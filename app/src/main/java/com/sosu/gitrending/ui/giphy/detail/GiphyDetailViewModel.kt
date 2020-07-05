package com.sosu.gitrending.ui.giphy.detail

import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.usecase.base.BaseUsecaseListener
import com.sosu.gitrending.usecase.giphy.GiphyGifsFavoriteRepoImpl
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class GiphyDetailViewModel @Inject constructor(
    private val giphyGifsRepoImpl: GiphyGifsRepoImpl,
    private val gifsFavoriteRepoImpl: GiphyGifsFavoriteRepoImpl
) : BaseViewModel<GiphyDetailNavigator>(){

    companion object{
        val TAG = GiphyDetailViewModel::class.java.simpleName
    }

    fun getGif() = giphyGifsRepoImpl.selectedGif

    override fun getName(): String {
        return TAG
    }

    fun onSelectedInfo(idx : Int){
        giphyGifsRepoImpl.onSelectTrending(idx)
    }

    fun isFavorite(id : String) : Boolean{
        return gifsFavoriteRepoImpl.isFavoriteHashSet(id)
    }

    fun onChangeFavorite(isSelect: Boolean){
        val giphyGif = getGif().value
        if(giphyGif != null){
            gifsFavoriteRepoImpl.setFavorite(giphyGif, isSelect, null)
        }
    }
}