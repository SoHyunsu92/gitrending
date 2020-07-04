package com.sosu.gitrending.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.databinding.ActivitySplashBinding
import com.sosu.gitrending.ui.base.BaseActivity
import com.sosu.gitrending.usecase.ui.StartActivityImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class SplashActivity
    : BaseActivity<ActivitySplashBinding, SplashViewModel>()
    , SplashNavigator {

    companion object{
        val TAG = SplashActivity::class.java.simpleName
    }

    private val splashViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    @Inject lateinit var startActivityImpl: StartActivityImpl

    override fun getName(): String {
        return TAG
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun initView() { }

    override fun initAfterBinding() {
        splashViewModel.setNavigator(this)
    }

    override fun onResume() {
        super.onResume()

        splashViewModel.onReadyForAppLoading()
    }

    override fun openMainActivity() {
        startActivity(startActivityImpl.openMainActivity())
    }

}