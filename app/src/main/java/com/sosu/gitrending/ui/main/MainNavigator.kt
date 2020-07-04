package com.sosu.gitrending.ui.main

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface MainNavigator {

    // bottom tab
    fun onShowedTrending()
    fun onShowedFavorite()

    // rv
    fun onInitPageFlags()
    fun onCompletedNextPage(nextPage : Int)
    fun onLastPage()
}