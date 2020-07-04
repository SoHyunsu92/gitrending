package com.sosu.gitrending.ui.main

import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class MainViewModel @Inject constructor(
) : BaseViewModel<MainNavigator>() {

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

    override fun getName(): String {
        return TAG
    }

    fun onShowTrending(){
        getNavigator()?.onShowedTrending()
    }

    fun onShowFavorite(){
        getNavigator()?.onShowedFavorite()
    }
}