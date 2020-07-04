package com.sosu.gitrending.di.module.app

import android.content.Context
import android.content.res.Resources
import com.sosu.gitrending.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideResource(application: BaseApplication): Resources {
        return application.resources
    }
}