package com.sosu.gitrending.utils

/**
 * Created by hyunsuso on 2020/07/06.
 */
object FileUtils {

    const val EXE_GIF = "gif"
    const val EXE_MP4 = "mp4"
    const val EXE_WEBP = "webp"

    fun findExe(filename : String?) : String{
        if(filename == null){
            return ""
        }
        return filename.split(".").findLast {
            return it
        }.toString()
    }
}