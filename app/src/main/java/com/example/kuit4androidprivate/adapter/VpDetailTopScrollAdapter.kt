package com.example.kuit4androidprivate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kuit4androidprivate.databinding.ItemDetailVpScrollBinding
import com.example.kuit4androidprivate.model.DetailTopVpData

class VpDetailTopScrollAdapter(private val itemCount : Int):
        ListAdapter<DetailTopVpData, VpDetailTopScrollAdapter.ViewHolder>(diffUtil) {
            inner class ViewHolder(private val binding: ItemDetailVpScrollBinding):
                    RecyclerView.ViewHolder(binding.root) {
                        fun bind(item : DetailTopVpData, position: Int) {
                            Glide.with(binding.root)
                                .load(item.imgId)
                                .into(binding.ivDetailVpTop)
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailVpScrollBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position % itemCount],position)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DetailTopVpData>() {
            override fun areItemsTheSame(
                oldItem: DetailTopVpData,
                newItem: DetailTopVpData
            ): Boolean {
                return oldItem.imgId == newItem.imgId
            }

            override fun areContentsTheSame(
                oldItem: DetailTopVpData,
                newItem: DetailTopVpData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}