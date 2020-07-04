package com.sosu.gitrending.ui.giphy.detail

import android.os.Handler
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.ui.giphy.GiphyDetailNavigator
import com.sosu.gitrending.ui.splash.SplashNavigator
import com.sosu.gitrending.ui.splash.SplashViewModel
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailViewModel @Inject constructor(

) : BaseViewModel<GiphyDetailNavigator>(){

    companion object{
        val TAG = GiphyDetailViewModel::class.java.simpleName
    }

    override fun getName(): String {
        return TAG
    }
}