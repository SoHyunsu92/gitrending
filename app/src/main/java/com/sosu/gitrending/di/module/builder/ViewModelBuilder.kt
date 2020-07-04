package com.sosu.gitrending.di.module.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.di.viewmodel.ViewModelFactory
import com.sosu.gitrending.di.viewmodel.ViewModelKey
import com.sosu.gitrending.ui.main.MainViewModel
import com.sosu.gitrending.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Module
abstract class ViewModelBuilder {

    /**
     * Binding ViewModel
     * ViewModelFactory key 로 ViewModelKey(ViewModel::class) 사용     */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    /*
    * Binds ViewModels factory to provide ViewModels
    * */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}