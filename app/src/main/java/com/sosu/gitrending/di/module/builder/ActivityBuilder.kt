package com.sosu.gitrending.di.module.builder

import com.sosu.gitrending.MainActivity
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
    abstract fun contributeMainActivity(): MainActivity
}