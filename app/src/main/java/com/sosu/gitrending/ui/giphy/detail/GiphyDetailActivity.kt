package com.sosu.gitrending.ui.giphy.detail

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.data.DataConstant.NULL_INT
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.databinding.ActivityGiphyDetailBinding
import com.sosu.gitrending.databinding.ActivitySplashBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.ui.giphy.GiphyDetailNavigator
import com.sosu.gitrending.ui.splash.SplashActivity
import com.sosu.gitrending.ui.splash.SplashNavigator
import com.sosu.gitrending.ui.splash.SplashViewModel
import com.sosu.gitrending.usecase.ui.StartActivityImpl
import com.sosu.gitrending.usecase.ui.StartActivityImpl.Companion.EXTRA_SELECT_IDX
import com.sosu.gitrending.utils.GlideUtils
import kotlinx.android.synthetic.main.activity_giphy_detail.*
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailActivity
    : BaseActivity<ActivityGiphyDetailBinding, GiphyDetailViewModel>()
    , GiphyDetailNavigator {

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

    override fun initView() { }

    override fun initAfterBinding() {
        giphyDetailViewModel.setNavigator(this)

        initLiveDataListener()

        onParseIntentExtra()
    }

    private fun onParseIntentExtra(){
        val selectIdx = intent.getIntExtra(EXTRA_SELECT_IDX, NULL_INT)

        onSelectInfo(selectIdx)
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
        // todo error_photo_30_w 다른 이미지로 변경하기 색 있는 걸로
        // todo size에 따라서 height 변경되게 하기
        GlideUtils.setSrcDefault(applicationContext, image_giphy_detail_activity__gif, giphyGif.images?.original?.getResUrl(), R.drawable.error_photo_30_w)

        GlideUtils.setSrcCenterCrop(applicationContext, image_giphy_detail_activity__user, giphyGif.user?.avatarUrl, R.drawable.error_photo_30_w)

        text_activity_giphy_detail__title.text = giphyGif.getTitle(applicationContext)
        text_activity_giphy_detail__username.text = giphyGif.getUsername(applicationContext)

        // todo favorite
    }

    private fun onSelectInfo(idx : Int){
        giphyDetailViewModel.onSelectInfo(idx)
    }
}