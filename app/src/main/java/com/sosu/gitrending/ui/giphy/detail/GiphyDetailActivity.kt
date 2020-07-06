package com.sosu.gitrending.ui.giphy.detail

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.alert.ToastMessage
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.databinding.ActivityGiphyDetailBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView.Companion.PAGE_START
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifViewHolder
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifsAdatper
import com.sosu.gitrending.usecase.ui.StartActivityImpl
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.EXTRA_GIPHY_GIF
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.RESULT_CODE_REFRESH_FAVORITE
import com.sosu.gitrending.utils.DeviceUtils
import com.sosu.gitrending.utils.GlideUtils
import com.sosu.gitrending.utils.GraphicUtils
import com.sosu.gitrending.utils.GsonUtils
import kotlinx.android.synthetic.main.activity_giphy_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailActivity
    : BaseActivity<ActivityGiphyDetailBinding, GiphyDetailViewModel>()
    , GiphyDetailNavigator, View.OnClickListener {

    companion object{
        val TAG = GiphyDetailActivity::class.java.simpleName
    }

    private val giphyDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(GiphyDetailViewModel::class.java)
    }

    @Inject
    lateinit var gifsAdapter : GiphyGifsAdatper
    private val baseRecyclerView by lazy {
        BaseRecyclerView(applicationContext, rv_giphy_detail_activity__rating_gifs, gifsAdapter)
    }

    @Inject
    lateinit var startActivityImpl: StartActivityImpl

    private var isChangedFavorite  = false

    override fun getName(): String {
        return TAG
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_giphy_detail
    }

    override fun getViewModel(): GiphyDetailViewModel {
        return giphyDetailViewModel
    }

    override fun initView() {
        btn_activity_giphy_detail__favorite.setOnClickListener(this)

        initGifsRecyclerView()
    }

    override fun initAfterBinding() {
        giphyDetailViewModel.setNavigator(this)

        initDetailGifListener()

        onParseIntentExtra()
    }

    override fun finish() {
        // favorite 변경된 경우, 갱신하기
        if(isChangedFavorite){
            onReturnIntent()
        }
        super.finish()
    }

    private fun onParseIntentExtra(){
        val giphyGif = GsonUtils.jsonStringToObject(intent.getStringExtra(EXTRA_GIPHY_GIF) ?: "", GiphyGif::class.java)

        if(giphyGif == null){
            showToast(ToastMessage(getString(R.string.str_not_exist_info), false))
            finish()
            return
        }

        setGiphyGifDetail(giphyGif)

        giphyDetailViewModel.getRemoteTrendingRatingGifs(PAGE_START)
    }

    // gifs recycler view 초기화
    private fun initGifsRecyclerView(){
        baseRecyclerView.initLinearLayoutManager(LinearLayoutManager.VERTICAL)
        baseRecyclerView.initStaggeredGridLayoutManager(GiphyGifsAdatper.GRID_MAIN_HOME)

        gifsAdapter.setCol(baseRecyclerView.getGridCol())
        gifsAdapter.setOnClickItemListener(object : GiphyGifViewHolder.OnClickItemListener{
            override fun onClickRoot(giphyGif: GiphyGif) {
                startActivity(startActivityImpl.openGiphyDetailActivity(giphyGif))
            }
        })

        // todo nested scroll
        // need to next rv paging
        baseRecyclerView.setOnPagingListener(object : BaseRecyclerView.PagingListener {
            override fun onNextPage(currentPage: Int) {
                giphyDetailViewModel.getRemoteTrendingRatingGifs(currentPage)
            }
        })

        /*
        * changed gifs data on repository
        * */
        giphyDetailViewModel.getRatingGifs().observe(this, Observer {
            gifsAdapter.addAllItem(it)
        })
    }

    private fun initDetailGifListener(){
        /*
        * changed gif data on repository
        * */
        giphyDetailViewModel.getDetailGif().observe(this, Observer {
            initInfo(it)
        })
    }

    // show data
    private fun initInfo(giphyGif: GiphyGif){
        initGifHeight(giphyGif)

        view_giphy_detail_activity__gif.setResUrl(giphyGif.images?.original?.getResUrl() ?: "")

        GlideUtils.setSrcCenterCrop(applicationContext, image_giphy_detail_activity__user, giphyGif.user?.avatarUrl, R.drawable.error_photo_30_w)

        text_activity_giphy_detail__title.text = giphyGif.getTitle(applicationContext)
        text_activity_giphy_detail__username.text = giphyGif.getUsername(applicationContext)

        image_activity_giphy_detail__auth.visibility = if(giphyGif.user?.isVerified == true) View.VISIBLE else View.GONE

        btn_activity_giphy_detail__favorite.isSelected = giphyDetailViewModel.isFavorite(giphyGif.id)
    }

    // gif height fix
    private fun initGifHeight(giphyGif: GiphyGif){
        val deviceWidth = DeviceUtils.getScreenWidth(applicationContext)

        // image frame
        val width = giphyGif.images?.previewGif?.width?.toFloat() ?: 1f
        val height = giphyGif.images?.previewGif?.height?.toFloat() ?: 1f

        view_giphy_detail_activity__gif.initHeight(GraphicUtils.getFrameHeightRatio(deviceWidth, width, height))
    }

    // set detail gif data
    private fun setGiphyGifDetail(giphyGif: GiphyGif){
        giphyDetailViewModel.setGiphyGifDetail(giphyGif)
    }

    // return activity result code
    // for refresh item of preActivity
    private fun onReturnIntent() {
        val returnIntent = Intent()
        setResult(RESULT_CODE_REFRESH_FAVORITE, returnIntent)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_activity_giphy_detail__favorite ->{
                onClickedFavorite()
            }
        }
    }

    // click favorite
    private fun onClickedFavorite(){
        val selecting = !btn_activity_giphy_detail__favorite.isSelected
        btn_activity_giphy_detail__favorite.isSelected = selecting

        isChangedFavorite = !isChangedFavorite

        giphyDetailViewModel.onChangeFavorite(selecting)
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