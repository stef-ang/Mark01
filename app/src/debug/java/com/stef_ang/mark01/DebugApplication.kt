package com.stef_ang.mark01

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.stef_ang.mark01.api.Api

class DebugApplication: Mark01Application() {

    override fun onCreate() {
        super.onCreate()
        initFlipper()
    }

    fun initFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {

            val client = AndroidFlipperClient.getInstance(this).apply {
                addPlugin(InspectorFlipperPlugin(this@DebugApplication, DescriptorMapping.withDefaults()))
                addPlugin(Api.networkFlipperPlugin)
                addPlugin(DatabasesFlipperPlugin(this@DebugApplication))
            }
            client.start()
        }
    }
}