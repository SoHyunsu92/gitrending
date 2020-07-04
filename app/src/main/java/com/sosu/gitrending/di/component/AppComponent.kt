package com.sosu.gitrending.di.component

import com.sosu.gitrending.BaseApplication
import com.sosu.gitrending.di.module.app.AppModule
import com.sosu.gitrending.di.module.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 *
 * app application 에 inject 하기 위한 component
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class
])
interface AppComponent{

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationBind(application: BaseApplication): Builder

        fun build() : AppComponent
    }
}