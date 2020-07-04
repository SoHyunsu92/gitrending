package com.sosu.gitrending.ui.main

import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.ui.base.BaseViewModel
import com.sosu.gitrending.ui.base.rv.BaseRecyclerView.Companion.PAGE_START
import com.sosu.gitrending.ui.component.list.giphy.GiphyGifsAdatper
import com.sosu.gitrending.usecase.giphy.GiphyGifsRepoImpl
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class MainViewModel @Inject constructor(
    private val giphyGifsRepoImpl: GiphyGifsRepoImpl
) : BaseViewModel<MainNavigator>() {

    companion object {
        val TAG = MainViewModel::class.java.simpleName
    }

    fun getGifs() = giphyGifsRepoImpl.gifs

    override fun getName(): String {
        return TAG
    }

    fun getRemoteTrendings(offset : Int){
        giphyGifsRepoImpl.getRemoteTrendings(offset, object : ApiStatusListener{
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

        })
    }

    fun onShowTrending(){
        getNavigator()?.onShowedTrending()

        getRemoteTrendings(PAGE_START)
    }

    fun onShowFavorite(){
        getNavigator()?.onShowedFavorite()

        // todo favorite
    }
}