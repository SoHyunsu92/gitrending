package com.sosu.gitrending.ui.component.list.giphy

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.base.rv.BaseRecyclerViewAdapterImpl
import com.sosu.gitrending.utils.GlideUtils
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
    * default
    * */
    inner class Default constructor(
        itemView: View
    ) : BaseRecyclerViewAdapterImpl.BaseViewHolder<GiphyGif>(itemView) {

        private val gifImage by lazy<ImageView> {
            itemView.findViewById(R.id.image_giphy_gifs_adapter__gif)
        }

        override fun onBind(item: GiphyGif) {
            // todo default 
            gifImage.layoutParams.height = item.images?.previewGif?.height ?: 100

            GlideUtils.setSrcCenterCrop(context, gifImage, item.images?.previewGif?.getResUrl(), R.drawable.error_photo_30_w)
        }
    }
}