package com.example.kuit4androidprivate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kuit4androidprivate.databinding.ItemHomeVpCardBinding
import com.example.kuit4androidprivate.model.VpCardData

class VpHomeAdapter : ListAdapter<VpCardData, VpHomeAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(val binding: ItemHomeVpCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VpCardData) {
            Glide.with(binding.root)
                .load(item.foodImgUrl)
                .into(binding.ivHomeVpCard)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VpHomeAdapter.ViewHolder {
        val binding =
            ItemHomeVpCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position % 5])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VpCardData>() {
            override fun areItemsTheSame(oldItem: VpCardData, newItem: VpCardData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VpCardData, newItem: VpCardData): Boolean {
                return oldItem == newItem
            }


        }
    }


}