package com.sosu.gitrending.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sosu.gitrending.data.model.app.DLog

/**
 * Created by hyunsuso on 2020/07/04.
 */
object GlideUtils {

    private val TAG = GlideUtils::class.java.simpleName

    fun setSrcDefault(context: Context?, thumbImage: ImageView, res: Any?, errorRes: Int) {
        if (context == null) {
            return
        }
        try {
            Glide.with(context)
                .load(res)
                .thumbnail(1.0f)
                .error(errorRes)
                .into(thumbImage)
        } catch (e: Exception) {
            DLog.e(TAG, e)
        }
    }

    fun setSrcCenterCrop(context: Context?, thumbImage: ImageView, res: Any?, errorRes: Int) {
        if (context == null) {
            return
        }
        try {
            Glide.with(context)
                .load(res)
                .thumbnail(1.0f)
                .error(errorRes)
                .apply(RequestOptions().centerCrop())
                .into(thumbImage)
        } catch (e: Exception) {
            DLog.e(TAG, e)
        }
    }
}