package com.sosu.gitrending.usecase.base

/**
 * Created by hyunsuso on 2020/07/05.
 */
interface BaseUsecaseListener{

    interface OnResultListener{
        fun onRefused(reason : String = "")
        fun onStarted()
        fun onSuccess()
        fun onFailed(error: String)
        fun onCompleted()
    }

    interface OnResultItemListener<T>{
        fun onRefused(reason : String = "")
        fun onStarted()
        fun onSuccess(result : T)
        fun onFailed(error: String)
        fun onCompleted()
    }
}
