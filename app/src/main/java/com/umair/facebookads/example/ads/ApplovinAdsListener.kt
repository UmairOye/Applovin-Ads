package com.umair.facebookads.example.ads

import android.app.Activity
import android.content.Context
import android.widget.FrameLayout
import com.applovin.mediation.ads.MaxInterstitialAd

interface ApplovinAdsListener {
    fun createBannerAd(context: Activity, banner: FrameLayout)
    fun requestNewInterstitial(interstitialAd: MaxInterstitialAd)
    fun showInterstitialAd(context: Activity)
    fun loadInterstitialAd(activity: Activity)
    fun showManualNativeAlertDialog(context: Activity)
    fun loadNativeAd(context: Activity)
    fun showNativeTemplateDialog(context: Activity)
    fun showToast(message: String, context: Context)
}