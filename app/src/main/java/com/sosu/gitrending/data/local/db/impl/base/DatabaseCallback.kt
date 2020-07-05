package com.sosu.gitrending.data.local.db.impl.base

import io.reactivex.observers.DisposableObserver

/**
 * Created by hyunsuso on 2020/07/05.
 */
class DatabaseCallback {

    // rxjava Observable callback
    class DBObservable<T> constructor(
        private val resultListener: ResultListener<T>
    ) : DisposableObserver<T>() {

        companion object{
            val TAG = DBObservable::class.java.simpleName
        }

        override fun onComplete() {
            resultListener.onCompleted()
        }

        override fun onNext(t: T) {
            resultListener.onSuccess(t)
        }

        override fun onError(e: Throwable) {
            resultListener.onFailed(e.message ?: "")
            resultListener.onCompleted()
        }
    }

    // view side callback
    interface ResultListener <T> {
        fun onSuccess(result: T)
        fun onFailed(error: String)
        fun onCompleted()
    }
}