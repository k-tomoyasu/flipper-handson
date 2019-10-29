package com.facebook.flipper.sample.tutorial

import android.content.Context
import android.preference.PreferenceManager
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.fresco.FrescoFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient

interface InitializationResult {
    val okHttpClient: OkHttpClient
}

object FlipperInitializer {
    fun initFlipper(context: Context): InitializationResult {
        SoLoader.init(context, false)

        Preferences.pref = PreferenceManager.getDefaultSharedPreferences(context)
        val networkFlipperPlugin = NetworkFlipperPlugin()
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(context)) {
            val flipperClient = AndroidFlipperClient.getInstance(context)
            val descriptorMapping = DescriptorMapping.withDefaults()
            flipperClient.addPlugin(InspectorFlipperPlugin(context, descriptorMapping))
            flipperClient.addPlugin(FrescoFlipperPlugin())
            flipperClient.addPlugin(SharedPreferencesFlipperPlugin(context))
            flipperClient.addPlugin(networkFlipperPlugin)
            flipperClient.start()
        }

        return object : InitializationResult {
            override val okHttpClient: OkHttpClient
                get() = OkHttpClient.Builder()
                        .addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
                        .build()
        }
    }
}