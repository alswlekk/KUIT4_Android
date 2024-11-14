package com.example.kuit4androidprivate.home

import MenuCategoryRVAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.kuit4androidprivate.R
import com.example.kuit4androidprivate.adapter.HomeScrollRVAdapter
import com.example.kuit4androidprivate.adapter.VpHomeAdapter
import com.example.kuit4androidprivate.databinding.FragmentHomeBinding
import com.example.kuit4androidprivate.detail.DetailActivity
import com.example.kuit4androidprivate.favorite.FavoriteActivity
import com.example.kuit4androidprivate.model.MenuCategoryData
import com.example.kuit4androidprivate.model.MenuData
import com.example.kuit4androidprivate.model.VpCardData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categoryRVAdapter: MenuCategoryRVAdapter
    private lateinit var scrollRVAdapter: HomeScrollRVAdapter
    private val dummyItemsFavorite = ArrayList<MenuData>()
    private val dummyItemsCategory = ArrayList<MenuCategoryData>()
    private lateinit var foodItems: ArrayList<String>

    private var homeVpItems = ArrayList<VpCardData>()

    private var currentPostion = 0
    private var mHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.sivOrderButton.setOnClickListener {
            val intent = Intent(activity, FavoriteActivity::class.java)
            startActivity(intent)

        }

//        initVPSwipe1()
//        initVPSwipe2()
//        initVPSwipe3()
//        initVPSwipe4() -> 쓰레드 blocking됨
//        initVPSwipe5()
//        initVPSwipe7()
//        initVPSwipe8()
//        CoroutineTest1()
        CoroutineTest5()

        initBottomDummy()
        initHomeScrollRVAdapter()
        inittopDummy()
        initCategoryRVAdapter()
        initData()
        initEditText()

        initFragmentHomeVP()

        return binding.root
    }

    private fun initFragmentHomeVP() {
        binding.vpHomeScroll.adapter = VpHomeAdapter().apply { submitList(homeVpItems) }
        binding.vpHomeScroll.currentItem = 1001
    }

    private fun initData() {
        foodItems = arrayListOf(
            "https://media.istockphoto.com/id/1653130581/ko/%EC%82%AC%EC%A7%84/%EC%95%BC%EC%B1%84-%EC%87%A0%EA%B3%A0%EA%B8%B0-%EA%B3%84%EB%9E%80%EC%9D%84-%EA%B3%81%EB%93%A4%EC%9D%B8-%EB%B0%A5.jpg?s=612x612&w=is&k=20&c=iDBAz2BJjOMuJA6iA16gNo2z6LkFJEmK7fOwn3GcnXc=",
            "https://cdn.pixabay.com/photo/2023/01/09/10/56/meal-7707134_640.jpg",
            "https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_640.jpg",
            "https://media.istockphoto.com/id/1366953086/ko/%EC%82%AC%EC%A7%84/%ED%95%9C%EA%B5%AD-%EC%9A%94%EB%A6%AC.jpg?s=612x612&w=is&k=20&c=xx77k5uaQ2TDTttCMQCHRfGxWlciGoR5DhCCk9kfCu8=",
            "https://media.istockphoto.com/id/1616721452/ko/%EC%82%AC%EC%A7%84/%EC%B4%88%EB%B0%A5%EA%B3%BC-%ED%95%A8%EA%BB%98-%EC%A0%90%EC%8B%AC%EC%9D%84-%EB%A8%B9%EB%8A%94-%EC%9D%B5%EB%AA%85%EC%9D%98-%EC%82%AC%EB%9E%8C%EB%93%A4.jpg?s=612x612&w=is&k=20&c=g8Eib-415My7ok_66QfL-RLANrZuXhMhuDLMF5brKIM="
        )

        for (i in 1 until 6) {
            homeVpItems.add(
                VpCardData(
                    id = i,
                    foodImgUrl = foodItems[i - 1]
                )
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.vp_home_scroll)
        val tvVpCounter = view.findViewById<TextView>(R.id.tv_vp_counter)

        val totalPages = 5

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentPage = (position % 5) + 1 // 현페이지는 1부터 시작

                tvVpCounter.text = "$currentPage/$totalPages"
            }
        })
    }

    private fun initCategoryRVAdapter() {
        categoryRVAdapter = MenuCategoryRVAdapter(this, dummyItemsCategory)
        with(binding.rvCategoryMenu) {
            adapter = categoryRVAdapter
            layoutManager = GridLayoutManager(requireContext(), 5)
        }
    }

    private fun inittopDummy() {
        dummyItemsCategory.addAll(
            arrayListOf(
                MenuCategoryData("돈가스", imageRes = R.drawable.img_porkcutlet),
                MenuCategoryData("한식", imageRes = R.drawable.img_koreanfood),
                MenuCategoryData("치킨", imageRes = R.drawable.img_chicken),
                MenuCategoryData("분식", imageRes = R.drawable.img_snackfood),
                MenuCategoryData("족발/보쌈", imageRes = R.drawable.img_jokbalbossam),
                MenuCategoryData("찜/탕", imageRes = R.drawable.img_soup),
                MenuCategoryData("구이", imageRes = R.drawable.img_roast),
                MenuCategoryData("피자", imageRes = R.drawable.img_pizza),
                MenuCategoryData("돈가스", imageRes = R.drawable.img_porkcutlet),
                MenuCategoryData("한식", imageRes = R.drawable.img_koreanfood),
                MenuCategoryData("치킨", imageRes = R.drawable.img_chicken),
                MenuCategoryData("분식", imageRes = R.drawable.img_snackfood)

            )
        )
    }

    private fun initHomeScrollRVAdapter() {
        scrollRVAdapter = HomeScrollRVAdapter(this, dummyItemsFavorite) { item ->
            val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
                putExtra("title", item.title)
                putExtra("imageUrl", item.imageUrl)
                putExtra("review", item.review)
                putExtra("count", item.count)

            }
            startActivity(intent)
        }
        with(binding.rvRecentlyMenu) {
            adapter = scrollRVAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

    }

    private fun initBottomDummy() {
        dummyItemsFavorite.addAll(
            arrayListOf(
                MenuData(
                    imageUrl = "https://media.istockphoto.com/id/1152570620/ko/%EC%82%AC%EC%A7%84/%EB%A7%A4%EC%9A%B4-%EB%96%A1.jpg?s=1024x1024&w=is&k=20&c=prL7yZ5TriVWZAuL5fg8I0Nyaw4stPNi6zgaA3zUBCg=",
                    title = "아워떡볶이",
                    time = "30분",
                    review = "4.5",
                    count = "(3,999)"
                ),
                MenuData(
                    imageUrl = "https://media.istockphoto.com/id/1368607122/ko/%EC%82%AC%EC%A7%84/%EB%B0%94%EC%82%AD%EB%B0%94%EC%82%AD%ED%95%9C-%EB%B9%B5%EC%9D%84-%ED%8A%80%EA%B8%B4-%EB%8B%AD%EA%B3%A0%EA%B8%B0-%EC%8A%A4%ED%8A%B8%EB%A6%BD-%EC%A0%91%EC%8B%9C%EC%97%90-%ED%86%A0%EB%A7%88%ED%86%A0-%EC%BC%80%EC%B2%A9%EC%9D%84-%EA%B3%81%EB%93%A4%EC%9D%B8-%EC%9C%A0%EB%B0%A9-%ED%95%84%EB%A0%9B-%EA%B3%A0%EA%B8%B0-%EB%82%98%EB%AC%B4-%EB%B0%B1%EA%B7%B8%EB%A3%AC-%EC%9C%84%EC%AA%BD-%EB%B3%B4%EA%B8%B0.jpg?s=612x612&w=0&k=20&c=xaB06WcG-eAjPlJTL0TDLu4FvCkDJ2FyYXfo_kPcC70=",
                    title = "신통치킨",
                    time = "25분",
                    review = "4.3",
                    count = "(3,000)"
                ),
                MenuData(
                    imageUrl = "https://media.istockphoto.com/id/1462352351/ko/%EC%82%AC%EC%A7%84/pho.jpg?s=1024x1024&w=is&k=20&c=8T2i6n1Pl2e1rw4-eeSpmmDhLNX2BAst34satRMUfhg=",
                    title = "미분당",
                    time = "13분",
                    review = "3.5",
                    count = "(1,023)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/1442417585/ko/%EC%82%AC%EC%A7%84/%EC%B9%98%EC%A6%88-%ED%8E%98%ED%8D%BC%EB%A1%9C%EB%8B%88-%ED%94%BC%EC%9E%90-%ED%95%9C-%EC%A1%B0%EA%B0%81%EC%9D%84-%EB%A8%B9%EB%8A%94-%EC%82%AC%EB%9E%8C.jpg?s=1024x1024&w=is&k=20&c=3DbLxIf6WkhVCoTHoOGhIThDvlfEYdP680HdcUS7ygc=",
                    "도미노피자",
                    "22분",
                    review = "3.7",
                    "(1,333)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/157640612/ko/%EC%82%AC%EC%A7%84/jajangmyeon-%EB%B8%94%EB%9E%99-bean-%EC%86%8C%EC%8A%A4-%EB%88%84%EB%93%A4%EC%8A%A4.jpg?s=1024x1024&w=is&k=20&c=LSVbtasaMTUCT9lM5ZgzeVbofybYUHulRMbgtv4OVUs=",
                    "홍콩반점",
                    "21분",
                    review = "2.2",
                    "(300)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/1189709277/ko/%EC%82%AC%EC%A7%84/%EA%B5%AC%EC%9A%B4-%ED%86%A0%EB%A7%88%ED%86%A0-%EC%86%8C%EC%8A%A4-%EB%AA%A8%EC%A7%9C%EB%A0%90%EB%9D%BC-%EC%B9%98%EC%A6%88%EB%A5%BC-%EA%B3%81%EB%93%A4%EC%9D%B8-%ED%8C%8C%EC%8A%A4%ED%83%80-%ED%8E%98%EB%84%A4-%ED%9A%8C%EC%83%89-%EB%8F%8C-%EB%B0%B0%EA%B2%BD%EC%9E%85%EB%8B%88%EB%8B%A4-%EB%A7%A8-%EC%9C%84-%EB%B3%B4%EA%B8%B0.jpg?s=1024x1024&w=is&k=20&c=j6eP1j1kgbbVd40x30kXJ7An2DFSqs8AQWvc4nan8_I=",
                    "서가앤쿡",
                    "13분",
                    review = "4.9",
                    "(2,143)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/610264540/ko/%EC%82%AC%EC%A7%84/%EC%BC%80%EC%9D%B4%EC%A4%80-%EC%83%88%EC%9A%B0%EC%9A%94.jpg?s=1024x1024&w=is&k=20&c=QvEeEC2vpA5WdinXT4E0SXygI5ysaEAe_9VZXgisxaU=",
                    "새우탕탕",
                    "26분",
                    review = "3.4",
                    "(1,490)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/694747604/ko/%EC%82%AC%EC%A7%84/curry.jpg?s=1024x1024&w=is&k=20&c=LwApGTRIzslVcssrHxdjeNq6WPU71-QLHfM_nJ9bmt8=",
                    "아비꼬카레",
                    "44분",
                    review = "3.3",
                    "(2,132)"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/525528747/ko/%EC%82%AC%EC%A7%84/%EC%97%B0%EB%A3%8C%EC%A6%9D%EA%B8%B0-%EC%A1%B1%EB%B0%9C-%EA%B7%B8%EB%A0%88%EC%9D%B4%EB%B9%84.jpg?s=1024x1024&w=is&k=20&c=zZbjRFaptHPqgGWqTlXzbIx2p_HSuJT3qW7B6Vwaps0=",
                    "건대족발",
                    "25분",
                    review = "3.2",
                    "4,532"
                ),
                MenuData(
                    "https://media.istockphoto.com/id/467867830/ko/%EC%82%AC%EC%A7%84/%EC%9D%BC%EB%B3%B8-%EC%9D%8C%EC%8B%9D-gyoza.jpg?s=1024x1024&w=is&k=20&c=IiMY_sjhoIiaq3RwgXpwnaP2UVxkrMW58rvHcfalEuM=",
                    "왕손만두",
                    "11분",
                    review = "3.1",
                    "(9,432)"
                )
            )
        )
    }

    private fun initEditText() {
        binding.etHomeSuggest.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etHomeSuggest.windowToken, 0)

                binding.etHomeSuggest.clearFocus()

                true
            } else {
                false
            }
        }
    }

    private fun swipePage() {
        with(binding.vpHomeScroll) {
            if (currentPostion > 4) currentPostion = 0
            setCurrentItem(currentPostion++, true)
        }
    }

    private fun initVPSwipe1() {
        while (true) {
            Thread.sleep(3000)
            swipePage()
        }
    }

    private fun initVPSwipe2() {
        var swipeThread = SwipeThread()
        swipeThread.start()
    }

    inner class SwipeThread : Thread() {
        override fun run() {
            while (true) {
                sleep(3000)
                swipePage()
            }
        }
    }

    private fun initVPSwipe3() { //v
        Thread(swipeRunnable()).start() // Runnable 구현된 게 Thread로 start
    }

    inner class mainHandler : Handler(Looper.getMainLooper()) { //v
        override fun handleMessage(msg: Message) {
            swipePage()
            super.handleMessage(msg)
        }
    }

    inner class swipeRunnable : Runnable { //v
        override fun run() {
            while (true) {
                sleep(3000) // 3초마다 화면 넘어감
                mainHandler().sendEmptyMessage(0)
            }
        }
    }

    private fun initVPSwipe4() {
        mainHandler2().sendEmptyMessage(0)
    }

    inner class mainHandler2 : Handler(Looper.getMainLooper()) { //v
        override fun handleMessage(msg: Message) {
            swipePage()
            Thread.sleep(3000)
            mainHandler2().sendEmptyMessage(0)
            super.handleMessage(msg)
        }
    }

    private fun initVPSwipe5() {
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                swipePage()
                mHandler.postDelayed(this, 3000)
            }
        }, 3000) // 비동기처리 지원

        binding.sivVpCounter.setOnClickListener {
            mHandler.removeCallbacksAndMessages(null)
        }

        binding.tvVpCounter.setOnClickListener {
            mHandler.removeCallbacksAndMessages(null)
        }
    }


    private fun initVPSwipe6() {
        mHandler.postDelayed({
            swipePage()
            initVPSwipe6()
        }, 3000) // 비동기처리 지원
    }

    private fun initVPSwipe7() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(3000)
                swipePage()
            }
        }
    }

    private fun initVPSwipe8() {
        var job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(3000)
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }
            }
        }

        binding.sivVpCounter.setOnClickListener {
            job.cancel()
        }
        binding.tvVpCounter.setOnClickListener {
            job.cancel()
        }
    }

    private fun CoroutineTest1() {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                launch { // 코루틴 안에서 코루틴을 실행
                    delay(3000)

                }.join() // join을 통해 코루틴이 끝날 때까지 기다림
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }
            }
        }
    }

    private fun CoroutineTest2() {
        var count = 0
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                launch { // 코루틴 안에서 코루틴을 실행
                    delay(3000)

                }.join() // join을 통해 코루틴이 끝날 때까지 기다림
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }
            }
        }
    }

    private fun CoroutineTest3() {
        var count = 0
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                count = async { // 코루틴 안에서 코루틴을 실행
                    delay(3000)
                    count + 10
                }.await() // join을 통해 코루틴이 끝날 때까지 기다림
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }

                Log.d("test", "count: $count")
            }
        }
    }

    private fun CoroutineTest4() {
        var count = 0
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                launch {
                    runBlocking {
                        delay(3000)
                        count += 10
                    }
                }

                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }

                Log.d("test", "count: $count")
            }
        }
    }

    private fun CoroutineTest5() {
        var count = 0
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                count = CoroutineDelay(count)
                withContext(Dispatchers.Main) { // Disptachers.Main에서 작동하도록 넘김
                    swipePage()
                }

                Log.d("test", "count: $count")
            }
        }
    }

    suspend fun CoroutineDelay(count: Int): Int {
        delay(3000)
        return count + 10

    }

}