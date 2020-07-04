package com.sosu.gitrending.data.model.app

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class AppInfo(
    var pkg: String,
    var name: String,
    var ver: String,
    var verCode: Int
) {

    companion object {
        val TAG = AppInfo::class.java.simpleName
    }
}