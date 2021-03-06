package com.sosu.gitrending.ui.main

import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView.Companion.PAGE_START
import com.sosu.gitrending.usecase.giphy.GiphyGifsFavoriteRepoImpl
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class MainViewModel @Inject constructor(
    private val giphyGifsRepoImpl: GiphyGifsRepoImpl,
    private val gifsFavoriteRepoImpl: GiphyGifsFavoriteRepoImpl
) : BaseViewModel<MainNavigator>() {

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

    fun getGifs() = giphyGifsRepoImpl.gifs
    fun getFavoriteGifs() = gifsFavoriteRepoImpl.gifs

    override fun getName(): String {
        return TAG
    }

    // get trending gifs api
    fun getRemoteTrendingGifs(offset : Int){
        addCompositeDisposable(giphyGifsRepoImpl.getRemoteTrendingGifs(offset, object : ApiStatusListener{
            override fun onStarted() {
                if(offset == PAGE_START){
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

    // find all favorite gifs
    private fun findAllFavoriteGifs(){
        getNavigator()?.onInitPageFlags()
        getNavigator()?.onLastPage()

        addCompositeDisposable(gifsFavoriteRepoImpl.findAll(null))
    }

    // showed trending view
    fun onShowTrending(){
        getNavigator()?.onShowedTrending()

        getRemoteTrendingGifs(PAGE_START)
    }

    // showed favorite view
    fun onShowFavorite(){
        getNavigator()?.onShowedFavorite()

        findAllFavoriteGifs()
    }
}