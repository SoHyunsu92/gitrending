package com.sosu.gitrending.ui.main

import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.databinding.ActivityMainBinding
import com.sosu.gitrending.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator{

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }

    private val mainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

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

    }

    override fun initAfterBinding() {
        mainViewModel.setNavigator(this)

        mainViewModel.onShowTrending()
    }

    // init bottom tab settings
    private fun initBottomTab(){
        text_main_activity__trending.isSelected = false
        text_main_activity__favorite.isSelected = false
    }

    override fun onShowedTrending() {
        initBottomTab()

        text_main_activity__trending.isSelected = true
    }

    override fun onShowedFavorite() {
        initBottomTab()

        text_main_activity__favorite.isSelected = true
    }
}