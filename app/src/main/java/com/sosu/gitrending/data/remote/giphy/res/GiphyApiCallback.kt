package com.sosu.gitrending.data.remote.giphy.res

import com.sosu.gitrending.data.DataConstant.NULL_DATA
import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.data.remote.base.error.NetworkError
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import io.reactivex.observers.DisposableObserver

/**
 * Created by hyunsuso on 2020/07/04.
 */
class GiphyApiCallback{

    // rxjava Observable callback
    class ApiObservable<T> constructor(
        private val resultListener: ApiResultListener<T>
    ) : DisposableObserver<GiphyApiBaseRes<T>>() {

        companion object{
            val TAG = ApiObservable::class.java.simpleName
        }

        override fun onComplete() {
            DLog.d(TAG, "onComplete")

            resultListener.onCompleted()
        }

        override fun onNext(t: GiphyApiBaseRes<T>) {
            DLog.d(TAG, "onNext $t")

            if(t.isError()){
                resultListener.onFailed(t.getError())
            } else {
                val data = t.data
                if(data == null){
                    resultListener.onFailed(NULL_DATA)
                } else{
                    resultListener.onSuccess(data)
                }
            }
        }

        override fun onError(e: Throwable) {
            DLog.e(TAG, "onError $e")

            val apiError = NetworkError.handleApiError(e)

            resultListener.onFailed(apiError.getFullMsg())
            resultListener.onCompleted()
        }
    }
}