package com.sosu.gitrending.ui.giphy.detail

import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.usecase.giphy.GiphyGifDetailRepoImpl
import com.sosu.gitrending.usecase.giphy.GiphyGifsFavoriteRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailViewModel @Inject constructor(
    private val gifDetailRepoImpl: GiphyGifDetailRepoImpl,
    private val gifsFavoriteRepoImpl: GiphyGifsFavoriteRepoImpl
) : BaseViewModel<GiphyDetailNavigator>(){

    companion object{
        val TAG = GiphyDetailViewModel::class.java.simpleName
    }

    fun getDetailGif() = gifDetailRepoImpl.detailGif

    override fun getName(): String {
        return TAG
    }

    fun setGiphyGifDetail(giphyGif: GiphyGif){
        gifDetailRepoImpl.setGiphyGifDetail(giphyGif)
    }

    fun isFavorite(id : String) : Boolean{
        return gifsFavoriteRepoImpl.isFavoriteHashSet(id)
    }

    fun onChangeFavorite(isSelect: Boolean){
        val giphyGif = getDetailGif().value
        if(giphyGif != null){
            addCompositeDisposable(gifsFavoriteRepoImpl.setFavorite(giphyGif, isSelect, null))
        }
    }
}