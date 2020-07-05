package com.sosu.gitrending.di.module.data

import dagger.Module

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Module(includes = [
    RetrofitModule::class,
    RoomDatabaseModule::class
])
class DataModule{

}