package com.sosu.gitrending.data.remote.giphy

import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.req.ApiEndPoint.END_POINT_GET_GIHPY_TRENDINGS
import com.sosu.gitrending.data.remote.base.req.ApiEndPoint.END_POINT_GET_GIHPY_TRENDINGS_RATING
import com.sosu.gitrending.data.remote.base.req.ApiParams.QUERY_api_key
import com.sosu.gitrending.data.remote.base.req.ApiParams.QUERY_limit
import com.sosu.gitrending.data.remote.base.req.ApiParams.QUERY_offset
import com.sosu.gitrending.data.remote.base.req.ApiParams.QUERY_rating
import com.sosu.gitrending.data.remote.giphy.res.GiphyApiBaseRes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface GiphyTrendingApi {

    companion object{
        const val OFFSET_DEFAULT = 25
    }

    @GET(END_POINT_GET_GIHPY_TRENDINGS)
    fun getTrendingGifs(
        @Query(QUERY_api_key) apiKey : String,
        @Query(QUERY_limit) limit : Int,
        @Query(QUERY_offset) offset : Int
    ): Observable<GiphyApiBaseRes<List<GiphyGif>>>

    @GET(END_POINT_GET_GIHPY_TRENDINGS_RATING)
    fun getTrendingRatingGifs(
        @Query(QUERY_api_key) apiKey : String,
        @Query(QUERY_rating) rating : String,
        @Query(QUERY_limit) limit : Int,
        @Query(QUERY_offset) offset : Int
    ): Observable<GiphyApiBaseRes<List<GiphyGif>>>
}