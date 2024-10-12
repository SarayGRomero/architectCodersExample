package com.architectcoders.rickandmortyapp.testrules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

@ExperimentalCoroutinesApi

class CoroutinesTestRule : TestInstancePostProcessor {

    private val testDispatcher = StandardTestDispatcher()

    override fun postProcessTestInstance(testInstance: Any, context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
        context?.let {
            it.getStore(ExtensionContext.Namespace.GLOBAL)
                .put("resetDispatcher", Runnable {
                    Dispatchers.resetMain()
                })
        }
    }

    fun clear() {
        Dispatchers.resetMain()
    }
}