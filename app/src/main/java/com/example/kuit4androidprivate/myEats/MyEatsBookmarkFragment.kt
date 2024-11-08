package com.example.kuit4androidprivate.myEats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.MyEatsBookMarkRVAdapter
import com.example.kuit4androidprivate.adapter.MyEatsReviewRVAdapter
import com.example.kuit4androidprivate.databinding.FragmentMyEatsBookmarkBinding
import com.example.kuit4androidprivate.model.MenuData

class MyEatsBookmarkFragment : Fragment() {
    private lateinit var binding: FragmentMyEatsBookmarkBinding
    private lateinit var myEatsBookMarkRVAdapter: MyEatsBookMarkRVAdapter
    private val bookmarkItem = arrayListOf<MenuData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyEatsBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBookmark()
        initMyEatBookMarkRVAdapter()
    }

    private fun initMyEatBookMarkRVAdapter() {
        myEatsBookMarkRVAdapter = MyEatsBookMarkRVAdapter(requireContext(), bookmarkItem)
        with(binding.rvBookmarkMenus) {
            adapter = myEatsBookMarkRVAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initBookmark() {
        bookmarkItem.addAll(
            arrayListOf(
                MenuData(
                    "https://media.istockphoto.com/id/1152570620/ko/%EC%82%AC%EC%A7%84/%EB%A7%A4%EC%9A%B4-%EB%96%A1.jpg?s=1024x1024&w=is&k=20&c=prL7yZ5TriVWZAuL5fg8I0Nyaw4stPNi6zgaA3zUBCg=",
                    "아워떡볶이",
                    "30분",
                    "4.5",
                    "(3,999)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/1368607122/ko/%EC%82%AC%EC%A7%84/%EB%B0%94%EC%82%AD%EB%B0%94%EC%82%AD%ED%95%9C-%EB%B9%B5%EC%9D%84-%ED%8A%80%EA%B8%B4-%EB%8B%AD%EA%B3%A0%EA%B8%B0-%EC%8A%A4%ED%8A%B8%EB%A6%BD-%EC%A0%91%EC%8B%9C%EC%97%90-%ED%86%A0%EB%A7%88%ED%86%A0-%EC%BC%80%EC%B2%A9%EC%9D%84-%EA%B3%81%EB%93%A4%EC%9D%B8-%EC%9C%A0%EB%B0%A9-%ED%95%84%EB%A0%9B-%EA%B3%A0%EA%B8%B0-%EB%82%98%EB%AC%B4-%EB%B0%B1%EA%B7%B8%EB%A3%AC-%EC%9C%84%EC%AA%BD-%EB%B3%B4%EA%B8%B0.jpg?s=612x612&w=0&k=20&c=xaB06WcG-eAjPlJTL0TDLu4FvCkDJ2FyYXfo_kPcC70=",
                    "신통치킨",
                    "25분",
                    "4.3",
                    "(3,000)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/1462352351/ko/%EC%82%AC%EC%A7%84/pho.jpg?s=1024x1024&w=is&k=20&c=8T2i6n1Pl2e1rw4-eeSpmmDhLNX2BAst34satRMUfhg=",
                    "미분당",
                    "13분",
                    "3.5",
                    "(1,023)"
                )
            )
        )
    }

}