package com.example.kuit4androidprivate.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.DetailDeliveryRVAdapter
import com.example.kuit4androidprivate.adapter.DetailWrappingRVAdapter
import com.example.kuit4androidprivate.databinding.FragmentDetailDeliveryBinding
import com.example.kuit4androidprivate.databinding.FragmentDetailWrappingBinding
import com.example.kuit4androidprivate.model.DetailMenuData
import java.util.Collections.addAll

class DetailWrappingFragment : Fragment() {
   private lateinit var binding: FragmentDetailWrappingBinding
   private lateinit var detailWrappingRVAdapter: DetailWrappingRVAdapter
   private val wrappingItem = arrayListOf<DetailMenuData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailWrappingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWrappingItem()
        initDetailWrappingRVAdapter()

}

    private fun initDetailWrappingRVAdapter() {
        detailWrappingRVAdapter = DetailWrappingRVAdapter(requireContext(),wrappingItem)
        with(binding.rvWrappingMenu) {
            adapter = detailWrappingRVAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        }
    }

    private fun initWrappingItem() {
        wrappingItem.addAll(
            arrayListOf(
                DetailMenuData(
                    menu = "떡볶이",
                    imgId = R.drawable.img_detail_ttok,
                    price = "5,000원"
                ),
                DetailMenuData(
                    menu = "김말이",
                    imgId = R.drawable.img_detail_seaweed,
                    price = "3,000원"
                ),
                DetailMenuData(
                    menu = "김밥",
                    imgId = R.drawable.img_detail_gimbap,
                    price = "3,500원"
                ),
                DetailMenuData(
                    menu = "돈까스",
                    imgId = R.drawable.img_detail_pork_cutlet,
                    price = "9,000원"
                )
            )
        )
    }
    }
