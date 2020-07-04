package com.sosu.gitrending.data.remote

import com.sosu.gitrending.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Singleton
class RetrofitApiImpl : RetrofitApi {

    companion object {

        val TAG = RetrofitApiImpl::class.java.simpleName

        /*
        * sec
        * */
        private const val CONNECT_TIMEOUT = 20L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 20L
    }

    private val giphyBaseUrl = BuildConfig.api_giphy

    override fun <T> giphyRetrofit(serviceClass: Class<T> , addHeaders: HashMap<String, String>): T {
        return Retrofit
            .Builder()
            .baseUrl(giphyBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClientHeader(addHeaders))
            .build()
            .create(serviceClass)
    }

    override fun createClientHeader(addHeaders: HashMap<String, String>): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);

        return httpClient.build()
    }

    override fun getResponseLog(request: Request): String{
        val requestMsg = request.toString()
        val headersMsg = request.headers
        val bodyMsg = request.body

        return "$requestMsg \n $headersMsg \n $bodyMsg"
    }
}
