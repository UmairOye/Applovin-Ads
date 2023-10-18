package com.umair.facebookads.example.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.umair.facebookads.example.databinding.FragmentAdPlacerBinding
import com.applovin.mediation.MaxAd
import com.applovin.mediation.nativeAds.adPlacer.MaxAdPlacer
import com.applovin.mediation.nativeAds.adPlacer.MaxAdPlacerSettings
import com.applovin.mediation.nativeAds.adPlacer.MaxRecyclerAdapter
import com.umair.facebookads.example.adapters.AdPlacerAdapter
import com.umair.facebookads.example.R
import com.umair.facebookads.example.utils.addOnBackPressedCallback

class AdPlacer : Fragment() {
    private var _binding: FragmentAdPlacerBinding? = null
    private val binding get() = _binding!!
    private val adPlacerList: ArrayList<Int> = ArrayList()
    private lateinit var adapter: AdPlacerAdapter
    private lateinit var adAdapter: MaxRecyclerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdPlacerBinding.inflate(inflater, container, false)
        addOnBackPressedCallback { findNavController().popBackStack() }

        val settings = MaxAdPlacerSettings(getString(R.string.applovinNativeSmall))
        settings.addFixedPosition(2)
        settings.addFixedPosition(5)
        settings.repeatingInterval = 5

        for (i in 0..25) {
            adPlacerList.add(i)
        }

        adapter = AdPlacerAdapter(adPlacerList)
        adAdapter = MaxRecyclerAdapter(settings, adapter, requireActivity())

        adAdapter.setListener(object : MaxAdPlacer.Listener {
            override fun onAdLoaded(position: Int) {
            }

            override fun onAdRemoved(position: Int) {
            }

            override fun onAdClicked(ad: MaxAd?) {
            }

            override fun onAdRevenuePaid(ad: MaxAd?) {}
        })

        binding.recyclerView.adapter = adAdapter
        binding.recyclerView.setHasFixedSize(true)

        adAdapter.loadAds()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        adAdapter.destroy()
        super.onDestroy()
    }
}