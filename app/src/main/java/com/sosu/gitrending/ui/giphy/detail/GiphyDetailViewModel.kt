package com.sosu.gitrending.ui.giphy.detail

import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView
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

    fun getRatingGifs() = gifDetailRepoImpl.ratingGifs
    fun getDetailGif() = gifDetailRepoImpl.detailGif

    // detail gif rating
    fun getDetailGifRating() = gifDetailRepoImpl.detailGif.value?.rating ?: ""

    override fun getName(): String {
        return TAG
    }

    // get trending rating gifs api
    fun getRemoteTrendingRatingGifs(offset : Int){
        addCompositeDisposable(gifDetailRepoImpl.getRemoteTrendingRatingGifs(getDetailGifRating(), offset, object :
            ApiStatusListener {
            override fun onStarted() {
                if(offset == BaseRecyclerView.PAGE_START){
                    getNavigator()?.onInitPageFlags()
                }
            }

            override fun onCompleted(error: String) {
                if(error.isNotEmpty()){
                    getNavigator()?.onLastPage()
                } else{
                    getNavigator()?.onCompletedNextPage(offset + 1)
                }
            }

        }))
    }

    // set gif detail data
    fun setGiphyGifDetail(giphyGif: GiphyGif){
        gifDetailRepoImpl.setGiphyGifDetail(giphyGif)
    }

    // check favorite
    fun isFavorite(id : String) : Boolean{
        return gifsFavoriteRepoImpl.isFavoriteHashSet(id)
    }

    // changed favorite
    fun onChangeFavorite(isSelect: Boolean){
        val giphyGif = getDetailGif().value
        if(giphyGif != null){
            addCompositeDisposable(gifsFavoriteRepoImpl.setFavorite(giphyGif, isSelect, null))
        }
    }
}