/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.flipper.sample.tutorial

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.fresco.FrescoFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.litho.config.ComponentsConfiguration
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin

class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
        Fresco.initialize(this)

        // Normally, you would want to make these dependent on BuildConfig.DEBUG.
        ComponentsConfiguration.isDebugModeEnabled = true
        ComponentsConfiguration.enableRenderInfoDebugging = true

        val flipperClient = AndroidFlipperClient.getInstance(this)
        val descriptorMapping = DescriptorMapping.withDefaults()
        val networkFlipperPlugin = NetworkFlipperPlugin()
        Network.client = OkHttpClient.Builder()
            .addInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
            .build()
        Preferences.pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        flipperClient.addPlugin(InspectorFlipperPlugin(this, descriptorMapping))
        flipperClient.addPlugin(FrescoFlipperPlugin())
        flipperClient.addPlugin(SharedPreferencesFlipperPlugin(applicationContext))
        flipperClient.addPlugin(networkFlipperPlugin)
        flipperClient.start()
    }
}

