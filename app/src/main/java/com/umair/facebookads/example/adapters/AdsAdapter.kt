package com.umair.facebookads.example.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umair.facebookads.example.databinding.ListItemAdsBinding

class AdsAdapter : RecyclerView.Adapter<AdsAdapter.AdsViewHolder>() {
    private var adsTypeList: List<String> = ArrayList()
    private var listener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val binding = ListItemAdsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return adsTypeList.size
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        holder.bind(adsTypeList[position])
        holder.cdMain.setOnClickListener { listener?.onItemClick(adsTypeList[position]) }
    }

    class AdsViewHolder(private val binding: ListItemAdsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cdMain = binding.cdMain
        fun bind(item: String) {
            binding.adType.text = item
            binding.adType.isSelected = true
        }
    }


    fun submitListToAdapter(adsTypeList: List<String>) {
        this.adsTypeList = adsTypeList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: String)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }
}