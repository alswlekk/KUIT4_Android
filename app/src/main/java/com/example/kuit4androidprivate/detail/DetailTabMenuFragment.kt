package com.example.kuit4androidprivate.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.DetailTabMenuAdapter
import com.example.kuit4androidprivate.databinding.FragmentDetailTabMenuBinding
import com.example.kuit4androidprivate.databinding.FragmentMyEatsTabMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailTabMenuFragment : Fragment() {

    private lateinit var binding: FragmentDetailTabMenuBinding
    private var tabItems = arrayListOf<String>("배달 30분", "포장 15분")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailTabMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentViewPager2()

    }

    private fun initFragmentViewPager2() {
        binding.vpDetail.adapter = DetailTabMenuAdapter(this)

        TabLayoutMediator(binding.tlDetailTab, binding.vpDetail) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }

}