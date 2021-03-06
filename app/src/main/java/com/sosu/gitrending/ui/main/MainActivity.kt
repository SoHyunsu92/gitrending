package com.sosu.gitrending.ui.main

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.databinding.ActivityMainBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifViewHolder
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifsAdatper
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifsAdatper.Companion.GRID_MAIN_HOME
import com.sosu.gitrending.usecase.ui.StartActivityImpl
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.REQUEST_CODE_GIPHY_DETAIL
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.RESULT_CODE_REFRESH_FAVORITE
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator{

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }

    private val mainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    @Inject
    lateinit var gifsAdapter : GiphyGifsAdatper
    private val baseRecyclerView by lazy {
        BaseRecyclerView(applicationContext, rv_main_activity__gifs, gifsAdapter)
    }

    @Inject
    lateinit var startActivityImpl: StartActivityImpl

    override fun getName(): String {
        return TAG
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun initView() {
        initGifsRecyclerView()
    }

    override fun initAfterBinding() {
        mainViewModel.setNavigator(this)

        mainViewModel.onShowTrending()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_GIPHY_DETAIL){
            if(resultCode == RESULT_CODE_REFRESH_FAVORITE && isShowedFavorite()){
                text_main_activity__favorite.performClick()
            }
        }
    }

    // gifs recycler view 초기화
    private fun initGifsRecyclerView(){
        baseRecyclerView.initLinearLayoutManager(LinearLayoutManager.VERTICAL)
        baseRecyclerView.initStaggeredGridLayoutManager(GRID_MAIN_HOME)

        gifsAdapter.setCol(baseRecyclerView.getGridCol())
        gifsAdapter.setOnClickItemListener(object : GiphyGifViewHolder.OnClickItemListener{
            override fun onClickRoot(giphyGif: GiphyGif) {
                startActivityForResult(startActivityImpl.openGiphyDetailActivity(giphyGif), REQUEST_CODE_GIPHY_DETAIL)
            }
        })

        // need to next rv paging
        baseRecyclerView.setOnPagingListener(object : BaseRecyclerView.PagingListener {
            override fun onNextPage(currentPage: Int) {
                mainViewModel.getRemoteTrendingGifs(currentPage)
            }
        })

        /*
        * changed gifs data on repository
        * */
        mainViewModel.getGifs().observe(this, Observer {
            gifsAdapter.addAllItem(it)
        })

        mainViewModel.getFavoriteGifs().observe(this, Observer {
            gifsAdapter.addAllItem(it)
        })
    }

    // init bottom tab settings
    private fun initBottomTab(){
        text_main_activity__trending.isSelected = false
        text_main_activity__favorite.isSelected = false
    }

    private fun isShowedFavorite() : Boolean{
        return text_main_activity__favorite.isSelected
    }

    // showed trending
    override fun onShowedTrending() {
        initBottomTab()

        text_main_activity__trending.isSelected = true
    }

    // showed favorite
    override fun onShowedFavorite() {
        initBottomTab()

        text_main_activity__favorite.isSelected = true
    }

    // page 데이터 초기화
    override fun onInitPageFlags() {
        baseRecyclerView.initPageFlags()
    }

    // 요청한 패이지 완료 했고, 다음 페이지 값 설정
    override fun onCompletedNextPage(nextPage: Int) {
        baseRecyclerView.onCompletedNextPage(nextPage)
    }

    // 페이징을 모두 끝냈다.
    override fun onLastPage() {
        baseRecyclerView.onLastPage()
    }
}