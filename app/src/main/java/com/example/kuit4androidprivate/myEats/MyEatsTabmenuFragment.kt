package com.example.kuit4androidprivate.myEats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuit4androidprivate.adapter.MyEatsTabMenuAdapter
import com.example.kuit4androidprivate.databinding.FragmentMyEatsTabMenuBinding
import com.google.android.material.tabs.TabLayoutMediator


class MyEatsTabmenuFragment : Fragment() {

    private lateinit var binding: FragmentMyEatsTabMenuBinding
    private var tabItems = arrayListOf<String>("리뷰", "즐겨찾기")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyEatsTabMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentViewPager2()
    }

    private fun initFragmentViewPager2() {
        binding.vpMyEats.adapter = MyEatsTabMenuAdapter(this)

        TabLayoutMediator(binding.tlMyEatsTab, binding.vpMyEats) { tab, position ->
            tab.text = tabItems[position]
        }.attach()
    }


}