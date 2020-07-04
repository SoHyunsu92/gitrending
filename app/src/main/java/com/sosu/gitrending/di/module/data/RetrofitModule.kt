package com.sosu.gitrending.di.module.data

import com.sosu.gitrending.data.remote.base.RetrofitApiImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitImpl(): RetrofitApiImpl {
        return RetrofitApiImpl()
    }
}