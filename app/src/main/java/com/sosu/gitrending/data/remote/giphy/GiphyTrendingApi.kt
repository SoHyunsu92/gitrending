package com.sosu.gitrending.data.remote.giphy

import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.req.ApiEndPoint.END_POINT_GET_GIHPY_TRENDINGS
import com.sosu.gitrending.data.remote.giphy.res.GiphyApiBaseRes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface GiphyTrendingApi {

    @GET(END_POINT_GET_GIHPY_TRENDINGS)
    fun getTrendings(
        // todo
    ): Observable<GiphyApiBaseRes<List<GiphyGif>>>
}