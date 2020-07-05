package com.sosu.gitrending.ui.giphy.detail

import android.os.Handler
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.ui.giphy.GiphyDetailNavigator
import com.sosu.gitrending.ui.splash.SplashNavigator
import com.sosu.gitrending.ui.splash.SplashViewModel
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailViewModel @Inject constructor(
    private val giphyGifsRepoImpl: GiphyGifsRepoImpl
) : BaseViewModel<GiphyDetailNavigator>(){

    companion object{
        val TAG = GiphyDetailViewModel::class.java.simpleName
    }

    fun getGif() = giphyGifsRepoImpl.selectedGif

    override fun getName(): String {
        return TAG
    }

    fun onSelectInfo(idx : Int){
        giphyGifsRepoImpl.onSelectTrending(idx)
    }
}