package com.sosu.gitrending.data.remote.base.res

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface ApiStatusListener {

    fun onStarted()
    fun onCompleted(error : String)
}