package com.umair.facebookads.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umair.facebookads.example.databinding.ListItemAdPlacerBinding

class AdPlacerAdapter(private val adsPlacerList: List<Int>) :
    RecyclerView.Adapter<AdPlacerAdapter.AdsPlacerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsPlacerViewHolder {
        val binding =
            ListItemAdPlacerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdsPlacerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adsPlacerList.size
    }

    override fun onBindViewHolder(holder: AdsPlacerViewHolder, position: Int) {
        holder.bind(adsPlacerList[position])
    }

    class AdsPlacerViewHolder(private val binding: ListItemAdPlacerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.tvAdPlacerCount.text = item.toString()
        }
    }


}