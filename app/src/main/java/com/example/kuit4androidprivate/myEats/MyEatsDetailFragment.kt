package com.example.kuit4androidprivate.myEats

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.databinding.FragmentMyEatsDetailBinding
import com.example.kuit4androidprivate.keep.KeepActivity

class MyEatsDetailFragment : Fragment() {

    private lateinit var binding: FragmentMyEatsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyEatsDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMyEatsDetail.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fcv_my_eats,MyEatsTabmenuFragment()).addToBackStack(null).commitAllowingStateLoss()
        }

        binding.clMyFavorite.setOnClickListener {
            val intent = Intent(activity, KeepActivity::class.java)
            startActivity(intent)
        }
    }
}