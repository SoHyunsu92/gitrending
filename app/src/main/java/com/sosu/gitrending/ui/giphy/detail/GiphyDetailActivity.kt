package com.sosu.gitrending.ui.giphy.detail

import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.databinding.ActivityGiphyDetailBinding
import com.sosu.gitrending.databinding.ActivitySplashBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.ui.giphy.GiphyDetailNavigator
import com.sosu.gitrending.ui.splash.SplashActivity
import com.sosu.gitrending.ui.splash.SplashNavigator
import com.sosu.gitrending.ui.splash.SplashViewModel
import com.sosu.gitrending.usecase.ui.StartActivityImpl
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
    }
}