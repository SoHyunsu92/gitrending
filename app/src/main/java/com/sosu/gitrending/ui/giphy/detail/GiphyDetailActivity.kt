package com.sosu.gitrending.ui.giphy.detail

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.alert.ToastMessage
import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.databinding.ActivityGiphyDetailBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.EXTRA_GIPHY_GIF
import com.sosu.gitrending.utils.DeviceUtils
import com.sosu.gitrending.utils.GlideUtils
import com.sosu.gitrending.utils.GraphicUtils
import com.sosu.gitrending.utils.GsonUtils
import kotlinx.android.synthetic.main.activity_giphy_detail.*

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
    }

    override fun initAfterBinding() {
        giphyDetailViewModel.setNavigator(this)

        initLiveDataListener()

        onParseIntentExtra()
    }

    private fun onParseIntentExtra(){
        val giphyGif = GsonUtils.jsonStringToObject(intent.getStringExtra(EXTRA_GIPHY_GIF) ?: "", GiphyGif::class.java)

        if(giphyGif == null){
            showToast(ToastMessage(getString(R.string.str_not_exist_info), false))
            finish()
            return
        }

        setGiphyGifDetail(giphyGif)
    }

    private fun initLiveDataListener(){
        /*
        * changed gif data on repository
        * */
        giphyDetailViewModel.getGif().observe(this, Observer {
            initInfo(it)
        })
    }

    private fun initInfo(giphyGif: GiphyGif){
        initGifHeight(giphyGif)

        view_giphy_detail_activity__gif.setResUrl(giphyGif.images?.original?.getResUrl() ?: "")

        GlideUtils.setSrcCenterCrop(applicationContext, image_giphy_detail_activity__user, giphyGif.user?.profileUrl, R.drawable.error_photo_30_w)

        text_activity_giphy_detail__title.text = giphyGif.getTitle(applicationContext)
        text_activity_giphy_detail__username.text = giphyGif.getUsername(applicationContext)

        btn_activity_giphy_detail__favorite.isSelected = giphyDetailViewModel.isFavorite(giphyGif.id)
    }

    private fun initGifHeight(giphyGif: GiphyGif){
        val deviceWidth = DeviceUtils.getScreenWidth(applicationContext)

        // image frame
        val width = giphyGif.images?.previewGif?.width?.toFloat() ?: 1f
        val height = giphyGif.images?.previewGif?.height?.toFloat() ?: 1f

        view_giphy_detail_activity__gif.initHeight(GraphicUtils.getFrameHeightRatio(deviceWidth, width, height))
    }

    private fun setGiphyGifDetail(giphyGif: GiphyGif){
        giphyDetailViewModel.setGiphyGifDetail(giphyGif)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_activity_giphy_detail__favorite ->{
                onClickedFavorite()
            }
        }
    }

    // todo 리스트 화면으로 가면 갱신할 수 있게 해주기
    private fun onClickedFavorite(){
        val selecting = !btn_activity_giphy_detail__favorite.isSelected
        btn_activity_giphy_detail__favorite.isSelected = selecting

        giphyDetailViewModel.onChangeFavorite(selecting)
    }
}