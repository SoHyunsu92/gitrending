package com.sosu.gitrending.data.model.app

import android.util.Log

/**
 * Created by hyunsuso on 2020/07/04.
 */
object DLog {

    val TAG = DLog::class.java.simpleName

    enum class LogType{
        NONE,
        ERROR,
        WARNING,
        INFO,
        DEBUG,
        VERBOSE
    }

    private val logLevel = LogType.VERBOSE

    fun e(TAG: String, log: String){
        if(DConfig.isDebugMode()) Log.e(TAG, log)
    }

    fun e(TAG: String, e: Throwable){
        if(DConfig.isDebugMode()) Log.e(TAG, Log.getStackTraceString(e))
    }

    fun w(TAG: String, log: String){
        if(DConfig.isDebugMode()) Log.w(TAG, log)
    }

    fun d(TAG: String, log: String){
        if(DConfig.isDebugMode()) Log.d(TAG, log)
    }
}