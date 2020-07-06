package com.sosu.gitrending.utils

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView

/**
 * Created by hyunsuso on 2020/07/06.
 */
object FrescoUtils {

    fun showWebpImage(imageView: SimpleDraweeView, uri: String?) {
        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
            .setUri(uri)
            .setAutoPlayAnimations(true)
            .build()
        imageView.controller = controller
    }
}