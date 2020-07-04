package com.sosu.gitrending.di.module.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosu.gitrending.di.viewmodel.ViewModelFactory
import com.sosu.gitrending.di.viewmodel.ViewModelKey
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
    // @memo 

    /*
    * Binds ViewModels factory to provide ViewModels
    * */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}