package com.sosu.gitrending.usecase.thread

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/05.
 */
class AppExecutorsImpl(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) : AppExecutors {

    companion object {
        private const val NETWORK_IO_THREAD_MAX = 5
    }

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(NETWORK_IO_THREAD_MAX),
        MainThreadExecutor()
    )

    override fun diskIO() = diskIO

    override fun networkIO() = networkIO

    override fun mainThread() = mainThread

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}