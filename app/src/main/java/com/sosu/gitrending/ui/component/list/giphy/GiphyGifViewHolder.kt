package com.sosu.gitrending.ui.component.list.giphy

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.base.rv.BaseRecyclerViewAdapterImpl
import com.sosu.gitrending.ui.component.custom.player.GifPlayerView
import com.sosu.gitrending.utils.DeviceUtils
import com.sosu.gitrending.utils.GlideUtils
import com.sosu.gitrending.utils.GraphicUtils
import kotlinx.android.synthetic.main.view_gif_player.view.*
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class GiphyGifViewHolder @Inject constructor(
    private val context: Context
) {

    companion object{
        val TAG = GiphyGifViewHolder::class.java.simpleName
    }

    /*
    * default grid
    * */
    inner class DefaultGrid constructor(
        itemView: View,
        col : Int,
        onClickItemListener: OnClickItemListener?
    ) : BaseRecyclerViewAdapterImpl.BaseViewHolder<GiphyGif>(itemView) {

        private val _onClickItemListener = onClickItemListener

        private val itemWidth by lazy {
            DeviceUtils.getScreenWidth(context) / col
        }

        private val gifPlayerView by lazy<GifPlayerView> { itemView.findViewById(R.id.view_giphy_gifs_adapter__gif_player) }

        override fun onBind(item: GiphyGif) {
            initImageHeight(item)

            gifPlayerView.setResUrl(item.images?.previewGif?.getResUrl() ?: "")

            initListener(item)
        }

        private fun initImageHeight(item: GiphyGif){
            // image frame
            val width = item.images?.previewGif?.width?.toFloat() ?: 1f
            val height = item.images?.previewGif?.height?.toFloat() ?: 1f

            gifPlayerView.initHeight(GraphicUtils.getFrameHeightRatio(itemWidth, width, height))
        }

        private fun initListener(item : GiphyGif){
            gifPlayerView.setOnClickListener{
                _onClickItemListener?.onClickRoot(item)
            }
        }
    }

    interface OnClickItemListener{
        fun onClickRoot(giphyGif: GiphyGif)
    }
}