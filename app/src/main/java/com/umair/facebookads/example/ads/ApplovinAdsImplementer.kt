package com.umair.facebookads.example.ads

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder
import com.umair.facebookads.example.R
import com.umair.facebookads.example.utils.Constants.TAG


class ApplovinAdsImplementer : ApplovinAdsListener {
    private var adView: MaxAdView? = null
    private var interstitialAd: MaxInterstitialAd? = null
    private var nativeAdLoader: MaxNativeAdLoader? = null
    private var nativeAdView: MaxNativeAdView? = null
    private var nativeAd: MaxAd? = null

    override fun createBannerAd(context: Activity, banner: FrameLayout) {
        adView = MaxAdView(context.getString(R.string.applovinBanner), context)

        /*if you want to set background color.
            adView?.setBackgroundColor(...)
         */
        banner.addView(adView)
        adView!!.loadAd()
        adView!!.startAutoRefresh()
        adView!!.setListener(object : MaxAdViewAdListener {
            override fun onAdLoaded(p0: MaxAd?) {
                Log.d(TAG, "onAdLoaded: Banner")
            }

            override fun onAdDisplayed(p0: MaxAd?) {
                Log.d(TAG, "onAdDisplayed: Banner")
            }

            override fun onAdHidden(p0: MaxAd?) {
                Log.d(TAG, "onAdHidden: Banner")
            }

            override fun onAdClicked(p0: MaxAd?) {
                Log.d(TAG, "onAdClicked: Banner")
            }

            override fun onAdLoadFailed(p0: String?, p1: MaxError?) {
                Log.d(TAG, "onAdLoadFailed: Banner ${p1!!.message}")
            }

            override fun onAdDisplayFailed(p0: MaxAd?, p1: MaxError?) {
                Log.d(TAG, "onAdDisplayFailed: Banner ${p1!!.message}")
            }

            override fun onAdExpanded(p0: MaxAd?) {
                Log.d(TAG, "onAdExpanded: Banner")
            }

            override fun onAdCollapsed(p0: MaxAd?) {
                Log.d(TAG, "onAdCollapsed: Banner")
            }

        })
    }


    override fun requestNewInterstitial(interstitialAd: MaxInterstitialAd) {
        interstitialAd.loadAd()
    }

    override fun loadInterstitialAd(activity: Activity) {
        interstitialAd =
            MaxInterstitialAd(activity.getString(R.string.applovinInterstitial), activity)
        requestNewInterstitial(interstitialAd!!)
    }

    override fun showInterstitialAd(context: Activity) {
        if (interstitialAd!!.isReady) {
            interstitialAd!!.showAd()
            interstitialAd!!.setListener(object : MaxAdViewAdListener {
                override fun onAdExpanded(ad: MaxAd) {}
                override fun onAdCollapsed(ad: MaxAd) {
                    Log.d(TAG, "onAdCollapsed: ")

                }

                override fun onAdLoaded(ad: MaxAd) {
                    Log.d(TAG, "onAdLoaded: ")
                }

                override fun onAdDisplayed(ad: MaxAd) {}
                override fun onAdHidden(ad: MaxAd) {
                    requestNewInterstitial(interstitialAd!!)
                    Log.d(TAG, "onAdHidden: ")

                }

                override fun onAdClicked(ad: MaxAd) {}
                override fun onAdLoadFailed(adUnitId: String, error: MaxError) {
                    requestNewInterstitial(interstitialAd!!)
                    Log.d(TAG, "onAdLoadFailed: ")
                }

                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                    requestNewInterstitial(interstitialAd!!)
                    Log.d(TAG, "onAdDisplayFailed: ")
                }
            })
        }

    }

    override fun loadNativeAd(context: Activity) {

    }

    private fun createNativeAdView(
        context: Activity,
        nativeAdLayout: FrameLayout
    ): MaxNativeAdLoader {

        val binder: MaxNativeAdViewBinder =
            MaxNativeAdViewBinder.Builder(R.layout.manual_native_view)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_text_view)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.options_view)
                .setStarRatingContentViewGroupId(R.id.star_rating_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build()
        nativeAdView = MaxNativeAdView(binder, context)
        nativeAdLoader =
            MaxNativeAdLoader(context.getString(R.string.applovinNativeManual), context)

        nativeAdLoader!!.setNativeAdListener(object : MaxNativeAdListener() {
            override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
                if (nativeAd != null) {
                    Log.d(TAG, "ifNativeAdIsNotNull: ")
                    nativeAdLoader!!.destroy(nativeAd)
                }

                nativeAd = ad
                nativeAdLayout.removeAllViews()
                nativeAdLayout.addView(nativeAdView)
            }

            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
            }

            override fun onNativeAdClicked(ad: MaxAd) {

            }

            override fun onNativeAdExpired(ad: MaxAd) {
            }

        })
        return nativeAdLoader as MaxNativeAdLoader
    }

    override fun showManualNativeAlertDialog(context: Activity) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater = context.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.custom_manual_native_dialog, null, false)
        val nativeAdLayout: FrameLayout = dialogView.findViewById(R.id.native_ad_layout)
        val nativeAd = createNativeAdView(context, nativeAdLayout)
        nativeAd.loadAd(nativeAdView)
        dialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog? = dialogBuilder.create()
        if (alertDialog != null) {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        if (alertDialog != null) {
            alertDialog.window?.attributes?.windowAnimations =
                R.style.DialogAnimation
        }
        alertDialog?.show()
    }

    private fun showNativeTemplate(context: Activity, nativeAdContainer: FrameLayout) {
        nativeAdLoader =
            MaxNativeAdLoader(context.getString(R.string.applovinNativeMedium), context)
        nativeAdLoader!!.setNativeAdListener(object : MaxNativeAdListener() {

            override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
                if (nativeAd != null) {
                    nativeAdLoader!!.destroy(nativeAd)
                }

                nativeAd = ad

                // Add ad view to view.
                nativeAdContainer.removeAllViews()
                nativeAdContainer.addView(nativeAdView)
            }

            override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
            }

            override fun onNativeAdClicked(ad: MaxAd) {
            }
        })
        nativeAdLoader!!.loadAd()
    }

    override fun showNativeTemplateDialog(context: Activity) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater = context.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.native_template, null, false)
        val nativeAdLayout: FrameLayout = dialogView.findViewById(R.id.native_template)
        showNativeTemplate(context, nativeAdLayout)
        dialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog? = dialogBuilder.create()
        if (alertDialog != null) {
            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        if (alertDialog != null) {
            alertDialog.window?.attributes?.windowAnimations =
                R.style.DialogAnimation
        }
        alertDialog?.show()
    }

    override fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}