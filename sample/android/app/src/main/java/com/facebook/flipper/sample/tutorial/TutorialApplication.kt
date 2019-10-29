/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.flipper.sample.tutorial

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.litho.config.ComponentsConfiguration

class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        // Normally, you would want to make these dependent on BuildConfig.DEBUG.
        ComponentsConfiguration.isDebugModeEnabled = true
        ComponentsConfiguration.enableRenderInfoDebugging = true

        val initResult = FlipperInitializer.initFlipper(context = this)
        Network.client = initResult.okHttpClient
    }
}

