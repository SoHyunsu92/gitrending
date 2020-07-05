package com.sosu.gitrending.usecase.thread

import java.util.concurrent.Executor

/**
 * Created by hyunsuso on 2020/07/05.
 */
interface AppExecutors {
    fun diskIO(): Executor
    fun networkIO(): Executor
    fun mainThread(): Executor
}