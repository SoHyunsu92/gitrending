package com.sosu.gitrending.data.remote.base.res

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface ApiResultListener <T> {
    fun onSuccess(result: T)
    fun onFailed(error: String)
    fun onCompleted()
}