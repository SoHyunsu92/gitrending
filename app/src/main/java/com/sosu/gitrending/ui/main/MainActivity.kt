package com.sosu.gitrending.ui.main

import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.BR
import com.sosu.gitrending.R
import com.sosu.gitrending.databinding.ActivityMainBinding
import com.sosu.gitrending.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

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

    }
}