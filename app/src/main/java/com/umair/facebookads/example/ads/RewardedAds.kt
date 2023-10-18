package com.umair.facebookads.example.ads

import android.app.Activity
import android.os.Handler
import android.widget.Toast
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener
import com.applovin.mediation.ads.MaxRewardedAd
import com.umair.facebookads.example.R

class RewardedAds(private val context: Activity) : MaxRewardedAdListener {
    private lateinit var rewardedAd: MaxRewardedAd
    private var retryAttempt = 0.0


    fun createRewardedAd() {
        rewardedAd = MaxRewardedAd.getInstance(context.getString(R.string.applovinReward), context)
        rewardedAd.setListener(this)
        rewardedAd.loadAd()
        if (rewardedAd.isReady) {
            rewardedAd.showAd()
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.reward_ad_is_not_ready_yet), Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onAdLoaded(maxAd: MaxAd) {
        retryAttempt = 0.0
    }

    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
        Toast.makeText(context, error!!.message.toString(), Toast.LENGTH_SHORT).show()
        retryAttempt++
        rewardedAd.loadAd()
    }

    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
        rewardedAd.loadAd()
    }

    override fun onAdDisplayed(maxAd: MaxAd) {}

    override fun onAdClicked(maxAd: MaxAd) {}

    override fun onAdHidden(maxAd: MaxAd) {
        rewardedAd.loadAd()
    }

    override fun onRewardedVideoStarted(maxAd: MaxAd) {}

    override fun onRewardedVideoCompleted(maxAd: MaxAd) {

    }

    override fun onUserRewarded(maxAd: MaxAd, maxReward: MaxReward) {

    }
}