package com.umair.facebookads.example.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.umair.facebookads.example.adapters.AdsAdapter
import com.umair.facebookads.example.ads.AppOpenManager
import com.umair.facebookads.example.ads.ApplovinAdsImplementer
import com.umair.facebookads.example.ads.ApplovinAdsListener
import com.umair.facebookads.example.R
import com.umair.facebookads.example.ads.RewardedAds
import com.umair.facebookads.example.utils.addOnBackPressedCallback
import com.umair.facebookads.example.databinding.FragmentHomeBinding


class Home : Fragment() , ApplovinAdsListener by ApplovinAdsImplementer() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AdsAdapter
    private var adsTypeList: ArrayList<String> = ArrayList()
    private lateinit var appOpenManager: AppOpenManager
    private lateinit var rewardedAds: RewardedAds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = AdsAdapter()
        createBannerAd(requireActivity(),binding.banner.bannerLayout)
        loadInterstitialAd(requireActivity())
        loadNativeAd(requireActivity())


        addOnBackPressedCallback {  }

        appOpenManager = AppOpenManager(requireContext().applicationContext)
        rewardedAds = RewardedAds(requireActivity())

        adsTypeList.clear()
        adsTypeList.apply {
            add(getString(R.string.interstitial))
            add(getString(R.string.native_ad_manual))
            add(getString(R.string.native_ad_placer))
            add(getString(R.string.native_template))
            add(getString(R.string.banner))
            add(getString(R.string.app_open))
            add(getString(R.string.rewarded))
        }

        adapter.submitListToAdapter(adsTypeList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.setOnClickListener(listener = object : AdsAdapter.OnClickListener {
            override fun onItemClick(item: String) {
                when (item) {
                    getString(R.string.interstitial) -> {
                        showInterstitialAd(requireActivity())
                    }
                    getString(R.string.native_ad_manual) -> {
                        showManualNativeAlertDialog(requireActivity())
                    }
                    getString(R.string.native_ad_placer) -> {
                        findNavController().navigate(R.id.action_home2_to_adPlacer)
                    }

                    getString(R.string.native_template) -> {
                        showNativeTemplateDialog(requireActivity())
                    }
                    getString(R.string.banner) -> {
                        showToast(getString(R.string.banner_ads_implemented_below), requireContext())
                    }
                    getString(R.string.app_open) -> {
                        appOpenManager.showAdIfReady()
                    }

                    getString(R.string.rewarded) -> {
                        rewardedAds.createRewardedAd()
                    }
                }
            }

        })


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}