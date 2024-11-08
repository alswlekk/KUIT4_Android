package com.example.kuit4androidprivate.keep

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.databinding.ActivityKeepBinding
import com.example.kuit4androidprivate.detail.DetailActivity
import com.example.kuit4androidprivate.model.MenuData

class KeepActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKeepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clOurrickcake.setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("title", "아워떡볶이")
                putExtra("imgId", R.drawable.img_detail_restaurant)
                putExtra("review", "4.9")
                putExtra("count", "(3,849)")

            }

            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}