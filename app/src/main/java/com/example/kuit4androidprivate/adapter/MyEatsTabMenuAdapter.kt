package com.example.kuit4androidprivate.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kuit4androidprivate.myEats.MyEatsBookmarkFragment
import com.example.kuit4androidprivate.myEats.MyEatsReviewFragment

class MyEatsTabMenuAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyEatsReviewFragment()
            else -> MyEatsBookmarkFragment()
        }
    }

}