package com.sosu.gitrending.ui.component.list.giphy

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.base.rv.BaseRecyclerViewAdapterImpl
import com.sosu.gitrending.utils.DeviceUtils
import com.sosu.gitrending.utils.GlideUtils
import com.sosu.gitrending.utils.GraphicUtils
import com.sosu.gitrending.utils.ResourceUtils
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
        col : Int
    ) : BaseRecyclerViewAdapterImpl.BaseViewHolder<GiphyGif>(itemView) {

        private val itemWidth by lazy {
            DeviceUtils.getScreenWidth(context) / col
        }

        private val rootView by lazy<View> { itemView.findViewById(R.id.container_giphy_gifs_adapter__root) }
        private val gifImage by lazy<ImageView> { itemView.findViewById(R.id.image_giphy_gifs_adapter__gif) }

        override fun onBind(item: GiphyGif) {
            initImageHeight(item)

            initMarginAcross()

            GlideUtils.setSrcCenterCrop(context, gifImage, item.images?.previewGif?.getResUrl(), R.drawable.error_photo_30_w)
        }

        private fun initImageHeight(item: GiphyGif){
            // image frame
            val width = item.images?.previewGif?.width?.toFloat() ?: 1f
            val height = item.images?.previewGif?.height?.toFloat() ?: 1f

            // dynamic height
            gifImage.layoutParams.height = GraphicUtils.getFrameHeightRatio(itemWidth, width, height)
        }

        private fun initMarginAcross(){
            val lp = rootView.layoutParams as RecyclerView.LayoutParams
            val acrossPx: Int = ResourceUtils.dimenToPx(context, R.dimen.adapter_giphy_gifs__frame_margin_across)
            var outsidePx = 0
            when(adapterPosition % 2){
                // left side
                0 -> {
                    lp.leftMargin = outsidePx
                    lp.rightMargin = acrossPx
                }
                // right side
                1 -> {
                    lp.leftMargin = acrossPx
                    lp.rightMargin = outsidePx
                }
            }

            rootView.layoutParams = lp
        }
    }
}