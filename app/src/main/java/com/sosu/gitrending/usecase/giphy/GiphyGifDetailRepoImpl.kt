package com.sosu.gitrending.usecase.giphy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sosu.gitrending.data.DataConstant
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.data.remote.giphy.GiphyTrendingRetrofitImpl
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/06.
 */
class GiphyGifDetailRepoImpl  @Inject constructor(
    private val giphyTrendingRetrofitImpl: GiphyTrendingRetrofitImpl
) : GiphyGifDetailRepo{

    companion object{
        val TAG = GiphyGifDetailRepoImpl::class.java.simpleName
    }

    // rating gifs
    private val _ratingGifs = MutableLiveData<List<GiphyGif>>()
    val ratingGifs: LiveData<List<GiphyGif>>
        get() = _ratingGifs

    // detail gif
    private val _detailGif = MutableLiveData<GiphyGif>()
    val detailGif: LiveData<GiphyGif>
        get() = _detailGif

    // get trending rating gifs api
    override fun getRemoteTrendingRatingGifs(
        rating: String,
        offset: Int,
        apiStatusListener: ApiStatusListener?
    ): Disposable {
        val disposable = giphyTrendingRetrofitImpl.getTrendingRatingGifs(rating, offset, object :
            ApiResultListener<List<GiphyGif>> {

            var error = ""

            override fun onSuccess(result: List<GiphyGif>) {
                if(result.isEmpty()){
                    this.error = DataConstant.NULL_DATA
                } else{
                    _ratingGifs.postValue(result)
                }
            }

            override fun onFailed(error: String) {
                this.error = error
            }

            override fun onCompleted() {
                apiStatusListener?.onCompleted(this.error)
            }
        })

        apiStatusListener?.onStarted()

        return disposable
    }

    // set detail gif
    override fun setGiphyGifDetail(giphyGif: GiphyGif) {
        _detailGif.value = giphyGif
    }
}