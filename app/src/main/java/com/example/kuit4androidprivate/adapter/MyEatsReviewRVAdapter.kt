package com.example.kuit4androidprivate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kuit4androidprivate.databinding.ItemMyEatsReviewBinding
import com.example.kuit4androidprivate.model.MyEatsReviewData

class MyEatsReviewRVAdapter(
    private val context: Context,
    private val items: ArrayList<MyEatsReviewData>
) : RecyclerView.Adapter<MyEatsReviewRVAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMyEatsReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyEatsReviewData) {
            binding.tvMyEatsReviewTitle.text = item.title
            binding.tvMyEatsReviewDate.text = item.date
            binding.tvReviewDetail.text = item.review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMyEatsReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyEatsReviewRVAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }


}