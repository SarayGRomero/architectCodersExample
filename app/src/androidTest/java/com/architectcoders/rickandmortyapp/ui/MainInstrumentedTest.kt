package com.architectcoders.rickandmortyapp.ui

import android.util.Log
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.architectcoders.rickandmortyapp.data.server.MockWebServerRule
import com.architectcoders.rickandmortyapp.data.server.fromJson
import com.architectcoders.rickandmortyapp.ui.screens.Navigation
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.internal.wait
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("characters.json")
        )

        //Log.d("MockResponse-url", mockWebServerRule.server.url("/").toString())
        //Log.d("MockResponse", MockResponse().fromJson("characters.json").toString())

        hiltRule.inject()

        val resource = OkHttp3IdlingResource.create("OkHTTPClient", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun click_on_character_and_navigate_to_detail() {
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithTag("characters_grid").onChildren()[3].performClick()

        composeTestRule.onNodeWithTag("character_4").assertIsDisplayed()
    }
}