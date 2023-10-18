package com.umair.facebookads.example.ads

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAppOpenAd
import com.applovin.sdk.AppLovinSdk
import com.umair.facebookads.example.R
import com.umair.facebookads.example.utils.Constants.TAG

class AppOpenManager(applicationContext: Context) : LifecycleObserver, MaxAdListener {
    private var appOpenAd: MaxAppOpenAd
    private var context = applicationContext


    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        context.let {
            appOpenAd = MaxAppOpenAd(context.getString(R.string.applovinOpen), it)
            appOpenAd.setListener(this)
            appOpenAd.loadAd()
        }
    }

    fun showAdIfReady() {
        if (!AppLovinSdk.getInstance(context).isInitialized) return
        appOpenAd?.let {
            if (appOpenAd.isReady) {
                appOpenAd.showAd()
            } else {
                appOpenAd.loadAd()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfReady()
    }

    override fun onAdLoaded(ad: MaxAd) {
    }

    override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
        appOpenAd.loadAd()
    }

    override fun onAdDisplayed(ad: MaxAd) {
        appOpenAd.loadAd()
    }

    override fun onAdClicked(ad: MaxAd) {}

    override fun onAdHidden(ad: MaxAd) {
        appOpenAd.loadAd()
    }

    override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
        appOpenAd.loadAd()
    }

}