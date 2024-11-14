package com.example.kuit4androidprivate.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.VpDetailTopScrollAdapter
import com.example.kuit4androidprivate.databinding.ActivityDetailBinding
import com.example.kuit4androidprivate.home.HomeFragment
import com.example.kuit4androidprivate.model.DetailMenuData
import com.example.kuit4androidprivate.model.DetailTopVpData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var vpTopItems = arrayListOf<DetailTopVpData>()
    private var currentPostion = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Base_Theme_Kuit4AndroidPrivate2) // 설정 시 "?attr/colorPrimary"의 색이 파란색으로, 가게 제목이 파란색으로 바뀜

        setTheme(R.style.Base_Theme_Kuit4AndroidNew)

        // window.statusBarColor = ContextCompat.getColor(this,R.color.suggest_box) // 맨위의 바색깔 변경(이 창꺼만 변경 원할 때)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVPTopItems()
        initVPDetailScrollAdapter()
        //initDetailTopSwipeVPCoroutine()
        initDetailTopSwipeVPThread()


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

    private fun initVPDetailScrollAdapter() {
        binding.vpDetailPic.adapter =
            VpDetailTopScrollAdapter(vpTopItems.size).apply { submitList(vpTopItems) }

        binding.vpDetailPic.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentPage = (position % vpTopItems.size) + 1
                val totalPages = vpTopItems.size

                binding.tvVpCounter.text = "$currentPage/$totalPages"
            }
        })
    }

    private fun initVPTopItems() {
        vpTopItems = arrayListOf(
            DetailTopVpData(R.drawable.img_greatsnack2),
            DetailTopVpData(R.drawable.img_greatsnack3),
            DetailTopVpData(R.drawable.img_greatsnack4)
        )
    }

    private fun swipePage() {
        with(binding.vpDetailPic) {
            if (currentPostion > 2) currentPostion = 0
            setCurrentItem(currentPostion++, true)
        }
    }

    // Thread를 이용한 자동 스와이프
    private fun initDetailTopSwipeVPThread() {
        Thread(swipeRunnable()).start() // Runnable 구현된 게 Thread로 start
    }

    inner class mainHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            swipePage()
            super.handleMessage(msg)
        }
    }

    inner class swipeRunnable : Runnable { //v
        override fun run() {
            while (true) {
                sleep(5000) // 5초마다 화면 넘어감
                mainHandler().sendEmptyMessage(0)
            }
        }
    }

    // Coroutine을 이용한 자동 스와이프
    private fun initDetailTopSwipeVPCoroutine() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                launch { // 코루틴 안에서 코루틴을 실행
                    delay(5000)

                }.join() // join을 통해 코루틴이 끝날 때까지 기다림
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }
            }
        }
    }

}