package com.sosu.gitrending.ui.giphy.detail

/**
 * Created by hyunsuso on 2020/07/05.
 */
interface GiphyDetailNavigator {

    // rv
    fun onInitPageFlags()
    fun onCompletedNextPage(nextPage : Int)
    fun onLastPage()
}