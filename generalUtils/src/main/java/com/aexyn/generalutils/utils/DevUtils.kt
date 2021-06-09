package com.aexyn.generalutils.utils

import android.content.Context
import com.aexyn.generalutils.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.OkHttpClient

open class DevUtils {
	companion object {
		private val networkFlipperPlugin = NetworkFlipperPlugin()
		fun initFlipper(context: Context) {
			SoLoader.init(context, false)
			if (FlipperUtils.shouldEnableFlipper(context)) {
				val client = AndroidFlipperClient.getInstance(context)
				client.addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
				client.addPlugin(NavigationFlipperPlugin.getInstance())
				client.addPlugin(networkFlipperPlugin)
				client.addPlugin(DatabasesFlipperPlugin(context))
				client.addPlugin(SharedPreferencesFlipperPlugin(context))
				client.start()
			}
		}

		fun attachNetworkInterceptor(builder: OkHttpClient.Builder) {
			builder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
		}
	}
}