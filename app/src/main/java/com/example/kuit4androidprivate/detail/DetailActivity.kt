package com.example.kuit4androidprivate.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.databinding.ActivityDetailBinding
import com.example.kuit4androidprivate.databinding.ItemFavoriteMenuBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Base_Theme_Kuit4AndroidPrivate2) // 설정 시 "?attr/colorPrimary"의 색이 파란색으로, 가게 제목이 파란색으로 바뀜

        setTheme(R.style.Base_Theme_Kuit4AndroidNew)

        // window.statusBarColor = ContextCompat.getColor(this,R.color.suggest_box) // 맨위의 바색깔 변경(이 창꺼만 변경 원할 때)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.getStringExtra("imageUrl") != null) {
            val imageUrl = intent.getStringExtra("imageUrl")
            Glide.with(this).load(imageUrl).into(binding.ivDetailPic)}
        else if(intent.getStringExtra("imgId") != null) {
            val imageUrl = intent.getStringExtra("imgId")
        }
        val title = intent.getStringExtra("title")
        val review = intent.getStringExtra("review")
        val count = intent.getStringExtra("count")

        binding.tvDetailTitle.text = title
        binding.tvDetailReview.text = review
        binding.tvDetailCount.text = count


        binding.ivBack.setOnClickListener() { finish() }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_detail, DetailTabMenuFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()


    }


}