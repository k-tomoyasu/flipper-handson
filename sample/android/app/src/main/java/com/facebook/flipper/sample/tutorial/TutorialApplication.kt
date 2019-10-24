/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.flipper.sample.tutorial

import android.app.Application
import android.preference.PreferenceManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.litho.config.ComponentsConfiguration
import okhttp3.OkHttpClient

class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        // Normally, you would want to make these dependent on BuildConfig.DEBUG.
        ComponentsConfiguration.isDebugModeEnabled = true
        ComponentsConfiguration.enableRenderInfoDebugging = true

        Network.client = OkHttpClient.Builder()
            .build()
        Preferences.pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }
}

