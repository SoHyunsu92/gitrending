package com.sosu.gitrending.data.remote.base

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface RetrofitApi {

    fun <T> giphyRetrofit(serviceClass: Class<T>, addHeaders: HashMap<String, String>): T

    fun createClientHeader(addHeaders: HashMap<String, String>): OkHttpClient
    fun getResponseLog(request: Request): String
}