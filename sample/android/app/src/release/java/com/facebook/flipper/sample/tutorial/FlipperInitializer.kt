package com.facebook.flipper.sample.tutorial

import android.content.Context
import okhttp3.OkHttpClient

interface InitializationResult {
    val okHttpClient: OkHttpClient
}

object FlipperInitializer {
    fun initFlipperPlugin(
        context: Context
    ): InitializationResult {
        return object : InitializationResult {
            override val okHttpClient: OkHttpClient
                get() = OkHttpClient.Builder()
                        .build()
        }
    }
}