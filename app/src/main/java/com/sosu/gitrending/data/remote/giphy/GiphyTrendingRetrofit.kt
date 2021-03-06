package com.sosu.gitrending.data.remote.giphy

import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import io.reactivex.disposables.Disposable

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface GiphyTrendingRetrofit {

    fun getTrendingGifs(
        offset: Int,
        resultListener: ApiResultListener<List<GiphyGif>>
    ): Disposable

    fun getTrendingRatingGifs(
        rating : String,
        offset: Int,
        resultListener: ApiResultListener<List<GiphyGif>>
    ): Disposable
}