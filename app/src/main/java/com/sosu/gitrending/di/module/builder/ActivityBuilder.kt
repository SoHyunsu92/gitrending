package com.sosu.gitrending.di.module.builder

import com.sosu.gitrending.ui.giphy.detail.GiphyDetailActivity
import com.sosu.gitrending.ui.main.MainActivity
import com.sosu.gitrending.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by hyunsuso on 2020/07/04.
 *
 * inject Activity
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeGiphyDetailActivity(): GiphyDetailActivity
}