package com.example.kuit4androidprivate.myEats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.MyEatsReviewRVAdapter
import com.example.kuit4androidprivate.databinding.FragmentMyEatsReviewBinding
import com.example.kuit4androidprivate.model.MyEatsReviewData

class MyEatsReviewFragment : Fragment() {

    private lateinit var binding: FragmentMyEatsReviewBinding
    private lateinit var myEatsReviewRVAdapter: MyEatsReviewRVAdapter
    private val reviewItem = arrayListOf<MyEatsReviewData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyEatsReviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initReviews()
        initMyEateReviewRVAdapter()
    }

    private fun initMyEateReviewRVAdapter() {
        myEatsReviewRVAdapter = MyEatsReviewRVAdapter(requireContext(), reviewItem)
        with(binding.rvReviews) {
            adapter = myEatsReviewRVAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initReviews() {
        reviewItem.addAll(
            arrayListOf(
                MyEatsReviewData(
                    "아워떡볶이", "맛있어요!추천드립니다.", "2024-04-11"
                ),
                MyEatsReviewData("미분당", "국물이 조금 차가웠어요ㅜ", "2024-06-13"),
                MyEatsReviewData("신통치킨", "가성비갑치킨이네여", "2024-07-01"),
                MyEatsReviewData(
                    "아워떡볶이", "맛있어요!추천드립니다.", "2024-04-11"
                ),
                MyEatsReviewData("미분당", "국물이 조금 차가웠어요ㅜ", "2024-06-13")
            )
        )
    }

}