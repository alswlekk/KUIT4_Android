package com.example.kuit4androidprivate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kuit4androidprivate.databinding.ItemDetailStoreMenuBinding
import com.example.kuit4androidprivate.model.DetailMenuData

class DetailWrappingRVAdapter(
    private val context: Context,
    private val items: ArrayList<DetailMenuData>
) : RecyclerView.Adapter<DetailWrappingRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemDetailStoreMenuBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item : DetailMenuData) {
            binding.tvMenuPrice.text = item.price
            binding.tvMenuName.text = item.menu
            if(item.imageUrl!= null) {
                Glide.with(context)
                    .load(item.imageUrl)
                    .into(binding.ivMenuPic)
            } else if(item.imgId!=null) {
                binding.ivMenuPic.setImageResource(item.imgId)
            }

        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailStoreMenuBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}