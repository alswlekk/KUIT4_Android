package com.example.kuit4androidprivate.myEats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.databinding.FragmentMyEatsBinding

class MyEatsFragment : Fragment() {

    private lateinit var binding: FragmentMyEatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyEatsBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().replace(R.id.fcv_my_eats, MyEatsDetailFragment())
            .addToBackStack(null).commit()

        showBackSpace()

        childFragmentManager.addOnBackStackChangedListener { showBackSpace() }

        binding.ivBack.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fcv_my_eats, MyEatsDetailFragment()).addToBackStack(null).commit()
        }
    }

    private fun showBackSpace() {
        val usingFragment = childFragmentManager.findFragmentById(R.id.fcv_my_eats)
        if (usingFragment is MyEatsDetailFragment) {
            binding.ivBack.visibility = View.GONE
        } else {
            binding.ivBack.visibility = View.VISIBLE
        }
    }

}