package com.umair.facebookads.example.applovinApp

import android.app.Application
import com.applovin.sdk.AppLovinSdk
import com.umair.facebookads.example.ads.AppOpenManager


class ApplovinApplication : Application() {
    private lateinit var appOpenManager: AppOpenManager


    override fun onCreate() {
        super.onCreate()
        AppLovinSdk.initializeSdk(this)
        appOpenManager = AppOpenManager(this)
        AppLovinSdk.getInstance(this).mediationProvider = "max"
    }

}