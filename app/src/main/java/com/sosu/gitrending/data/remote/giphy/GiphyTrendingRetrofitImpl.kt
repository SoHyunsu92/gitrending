package com.sosu.gitrending.data.remote.giphy

import com.sosu.gitrending.BuildConfig
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.RetrofitApiImpl
import com.sosu.gitrending.data.remote.base.req.ApiHeader
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import com.sosu.gitrending.data.remote.giphy.GiphyTrendingApi.Companion.OFFSET_DEFAULT
import com.sosu.gitrending.data.remote.giphy.res.GiphyApiCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Singleton
class GiphyTrendingRetrofitImpl @Inject constructor(
    private val retrofitApiImpl: RetrofitApiImpl
) : GiphyTrendingRetrofit {

    override fun getTrendingGifs(
        offset: Int,
        resultListener: ApiResultListener<List<GiphyGif>>
    ): Disposable {
        return retrofitApiImpl
            .giphyRetrofit(GiphyTrendingApi::class.java, ApiHeader.getDefault())
            .getTrendingGifs(BuildConfig.key_api_giphy, offset, OFFSET_DEFAULT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(GiphyApiCallback.ApiObservable(resultListener))
    }

    override fun getTrendingRatingGifs(
        rating: String,
        offset: Int,
        resultListener: ApiResultListener<List<GiphyGif>>
    ): Disposable {
        return retrofitApiImpl
            .giphyRetrofit(GiphyTrendingApi::class.java, ApiHeader.getDefault())
            .getTrendingRatingGifs(BuildConfig.key_api_giphy, rating, offset, OFFSET_DEFAULT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(GiphyApiCallback.ApiObservable(resultListener))
    }


}