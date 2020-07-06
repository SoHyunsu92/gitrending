package com.sosu.gitrending.ui.component.custom.player

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.ui.base.BaseView
import com.sosu.gitrending.utils.FileUtils
import com.sosu.gitrending.utils.FileUtils.EXE_GIF
import com.sosu.gitrending.utils.FileUtils.EXE_WEBP
import com.sosu.gitrending.utils.FrescoUtils
import com.sosu.gitrending.utils.GlideUtils
import kotlinx.android.synthetic.main.view_gif_player.view.*
import java.util.*
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/06.
 */
class GifPlayerView : BaseView {

    companion object {
        val TAG = GifPlayerView::class.java.simpleName
    }

    /* todo mp4 */
    private val bgColor = intArrayOf(
        R.color.colorBGGif1,
        R.color.colorBGGif2,
        R.color.colorBGGif3,
        R.color.colorBGGif4,
        R.color.colorBGGif5
    )
    private var resUrl = ""

    constructor(context: Context) : super(context) { }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { }

    override fun getName(): String {
        return TAG
    }

    override fun layoutResId(): Int {
        return R.layout.view_gif_player
    }

    override fun initView() {
        container_gif_player_view__root.setBackgroundColor(ContextCompat.getColor(context, randomColor()))
    }

    override fun initInfo() {
        onShowGif(resUrl)
    }

    private fun initViewVisible(){
        image_gif_player_view__gif.visibility = View.GONE
        webp_gif_player_view__gif.visibility = View.GONE
    }

    private fun onShowGif(resUrl: String){
        initViewVisible()

        when(FileUtils.findExe(resUrl)){
            EXE_GIF -> {
                image_gif_player_view__gif.visibility = View.VISIBLE
                GlideUtils.setSrcCenterCrop(context, image_gif_player_view__gif, resUrl, R.drawable.error_photo_30_w)
            }
            EXE_WEBP -> {
                webp_gif_player_view__gif.visibility = View.VISIBLE
                FrescoUtils.showWebpImage(webp_gif_player_view__gif, resUrl)
            }
        }
    }

    fun initHeight(height : Int){
        webp_gif_player_view__gif.layoutParams.height = height
        image_gif_player_view__gif.layoutParams.height = height
    }

    fun setResUrl(resUrl : String){
        this.resUrl = resUrl

        onShowGif(resUrl)
    }

    private fun randomColor() : Int {
        val colorIdx = Random().nextInt(5)

        return bgColor[colorIdx]
    }
}