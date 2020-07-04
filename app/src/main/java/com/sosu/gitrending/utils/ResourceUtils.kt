package com.sosu.gitrending.utils

import android.content.Context

/**
 * Created by hyunsuso on 2020/07/05.
 */
object ResourceUtils {

    fun dimenToPx(context: Context, resId: Int): Int {
        return context.resources.getDimension(resId).toInt()
    }
}