package com.sosu.gitrending.ui.component.list.giphy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sosu.gitrending.R
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.base.rv.BaseRecyclerViewAdapterImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class GiphyGifsAdatper @Inject constructor(
    private val context: Context
) : BaseRecyclerViewAdapterImpl<GiphyGif, BaseRecyclerViewAdapterImpl.BaseViewHolder<GiphyGif>>() {

    companion object{
        val TAG = GiphyGifsAdatper::class.java.simpleName

        const val GRID_DEFAULT = 1
        const val GRID_MAIN_HOME = 2
    }

    private var col = GRID_DEFAULT
    private var onClickItemListener: GiphyGifViewHolder.OnClickItemListener? = null

    @Inject lateinit var giphyGifViewHolder : GiphyGifViewHolder

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.adapter_giphy_gifs
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GiphyGif> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)

        return giphyGifViewHolder.DefaultGrid(view, col, onClickItemListener)
    }

    fun setCol(col : Int){
        this.col = col
    }

    fun setOnClickItemListener(onClickItemListener: GiphyGifViewHolder.OnClickItemListener){
        this.onClickItemListener = onClickItemListener
    }
}
