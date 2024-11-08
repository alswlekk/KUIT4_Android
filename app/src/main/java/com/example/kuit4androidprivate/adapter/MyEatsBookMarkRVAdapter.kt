package com.example.kuit4androidprivate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kuit4androidprivate.databinding.ItemMyEatsBookmarkBinding
import com.example.kuit4androidprivate.model.MenuData

class MyEatsBookMarkRVAdapter(
    private val context: Context,
    private val items: ArrayList<MenuData>
) : RecyclerView.Adapter<MyEatsBookMarkRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMyEatsBookmarkBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(item : MenuData) {
                    binding.tvBookmarkTitle.text = item.title
                    binding.tvBookmarkReview.text = item.review
                    binding.tvBookmarkCount.text = item.count

                    Glide.with(context)
                        .load(item.imageUrl)
                        .into(binding.ivMyEatsBookmark)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyEatsBookmarkBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int=items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}

