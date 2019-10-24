/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.flipper.sample.tutorial.ui

import com.facebook.flipper.sample.tutorial.MarineMammal
import com.facebook.flipper.sample.tutorial.Network
import com.facebook.flipper.sample.tutorial.Preferences
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Card

import com.facebook.yoga.YogaEdge.HORIZONTAL
import com.facebook.yoga.YogaEdge.VERTICAL
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

@LayoutSpec
object FeedItemCardSpec {

  @OnCreateLayout
  fun onCreateLayout(
      c: ComponentContext,
      @Prop mammal: MarineMammal
  ): Component =
      Column.create(c)
          .paddingDip(VERTICAL, 8f)
          .paddingDip(HORIZONTAL, 16f)
          .clickHandler(FeedItemCard.onClickEvent(c))
          .child(
              Card.create(c)
                  .content(
                      MarineMammelComponent.create(c)
                          .mammal(mammal)))
          .build()

    @OnEvent(ClickEvent::class)
    fun onClickEvent(c: ComponentContext) {
        val request = Request.Builder().url("https://hacker-news.firebaseio.com/v0/item/8863.json").build()
        Network.client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Preferences.pref.edit().putString("response", response.body()?.string()).apply()
            }

            override fun onFailure(call: Call, e: IOException) {

            }
        })
    }
}
