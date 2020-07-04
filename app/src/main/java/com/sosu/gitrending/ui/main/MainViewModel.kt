package com.sosu.gitrending.ui.main

import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class MainViewModel @Inject constructor(
    private val gifsRepoImpl: GiphyGifsRepoImpl
) : BaseViewModel<MainNavigator>() {

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

    override fun getName(): String {
        return TAG
    }

    init {
        // todo
        gifsRepoImpl.getRemoteTrendings(1, null)
    }

}