package com.sosu.gitrending.utils

/**
 * Created by hyunsuso on 2020/07/05.
 */
object GraphicUtils {

    // width ratio -> height
    fun getFrameHeightRatio(width: Int, ratioWidth: Float, ratioHeight: Float): Int {
        if(ratioWidth == 0f){
            return 0
        }
        return (width * (ratioHeight / ratioWidth)).toInt()
    }
}