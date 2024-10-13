package com.architectcoders.rickandmortyapp.ui

import androidx.annotation.CheckResult
import androidx.test.espresso.IdlingResource
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class OkHttp3IdlingResource private constructor(
    private val name: String,
    private val dispatcher: Dispatcher
): IdlingResource {

    @Volatile
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName() = name

    override fun isIdleNow(): Boolean = dispatcher.runningCallsCount() == 0

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

    companion object {
        @CheckResult
        fun create(name: String, client: OkHttpClient): OkHttp3IdlingResource {
            return OkHttp3IdlingResource(name, client.dispatcher)
        }
    }

    init {
        dispatcher.idleCallback = Runnable { resourceCallback?.onTransitionToIdle() }
    }
}