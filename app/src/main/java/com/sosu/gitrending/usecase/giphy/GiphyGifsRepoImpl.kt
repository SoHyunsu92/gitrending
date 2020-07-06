package com.sosu.gitrending.usecase.giphy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sosu.gitrending.data.DataConstant.NULL_DATA
import com.sosu.gitrending.data.DataConstant.NULL_INT
import com.sosu.gitrending.data.model.app.DLog
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.data.remote.giphy.GiphyTrendingRetrofitImpl
import com.sosu.gitrending.ui.giphy.detail.GiphyDetailActivity
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Singleton
class GiphyGifsRepoImpl @Inject constructor(
    private val giphyTrendingRetrofitImpl: GiphyTrendingRetrofitImpl
) : GiphyGifsRepo{

    companion object{
        val TAG = GiphyGifsRepoImpl::class.java.simpleName
    }

    private val _gifs = MutableLiveData<List<GiphyGif>>()
    val gifs: LiveData<List<GiphyGif>>
        get() = _gifs

    private val _selectedGif = MutableLiveData<GiphyGif>()
    val selectedGif: LiveData<GiphyGif>
        get() = _selectedGif

    /*
    * trending giphy gifs
    * @memo 테스트 api 이기 때문에 페이지에서 중복된 데이터가 계속 내려온다.
    * */
    override fun getRemoteTrendings(
        offset : Int,
        apiStatusListener: ApiStatusListener?
    ) : Disposable{
        val disposable = giphyTrendingRetrofitImpl.getTrendings(offset, object : ApiResultListener<List<GiphyGif>>{

            var error = ""

            override fun onSuccess(result: List<GiphyGif>) {
                if(result.isEmpty()){
                    this.error = NULL_DATA
                } else{
                    _gifs.postValue(result)
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

    override fun setGiphyGifDetail(giphyGif: GiphyGif) {
        _selectedGif.value = giphyGif
    }
}