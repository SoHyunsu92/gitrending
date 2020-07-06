package com.sosu.gitrending.usecase.giphy

import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import io.reactivex.disposables.Disposable

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface GiphyGifsRepo{

    // get remote giphy gifs trending
    fun getRemoteTrendingGifs(
        offset : Int,
        apiStatusListener: ApiStatusListener?
    ) : Disposable
}