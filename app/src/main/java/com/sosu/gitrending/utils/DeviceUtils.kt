package com.sosu.gitrending.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by hyunsuso on 2020/07/05.
 */
object DeviceUtils {

    // 화면 너비 반환 (pixel)
    fun getScreenWidth(context: Context?): Int {
        if (context == null) {
            return 0
        }
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()

        wm.defaultDisplay.getMetrics(dm)

        return dm.widthPixels
    }
}