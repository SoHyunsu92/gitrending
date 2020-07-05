package com.sosu.gitrending.usecase.giphy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sosu.gitrending.data.DataConstant
import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity
import com.sosu.gitrending.data.local.db.entity.toGiphyGif
import com.sosu.gitrending.data.local.db.entity.toGiphyGifs
import com.sosu.gitrending.data.local.db.impl.base.DatabaseCallback
import com.sosu.gitrending.data.local.db.impl.giphy.GiphyGifFavoriteDatabaseImpl
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.model.giphy.toGiphyGifFavoriteEntity
import com.sosu.gitrending.data.remote.base.res.ApiResultListener
import com.sosu.gitrending.data.remote.base.res.ApiStatusListener
import com.sosu.gitrending.data.remote.giphy.GiphyTrendingRetrofitImpl
import com.sosu.gitrending.usecase.base.BaseUsecaseListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/05.
 */
@Singleton
class GiphyGifsFavoriteRepoImpl @Inject constructor(
    private val gifFavoriteDatabaseImpl: GiphyGifFavoriteDatabaseImpl
) : GiphyGifsFavoriteRepo{

    companion object{
        val TAG = GiphyGifsFavoriteRepoImpl::class.java.simpleName
    }

    private val _gifs = MutableLiveData<List<GiphyGif>>()
    val gifs: LiveData<List<GiphyGif>>
        get() = _gifs

    // is giphy gif favorite
    private var favoriteIdSet : HashSet<String>? = null

    init {
        findAll(null)
    }

    override fun findOne(
        id: String,
        resultListener: BaseUsecaseListener.OnResultItemListener<GiphyGif>?
    ): Disposable {
        val disposable = gifFavoriteDatabaseImpl.findOne(id, object : DatabaseCallback.ResultListener<GiphyGifFavoriteEntity> {
            override fun onSuccess(result: GiphyGifFavoriteEntity) {
                addFavoriteHashSet(result.id)

                resultListener?.onSuccess(result.toGiphyGif)
            }

            override fun onFailed(error: String) {
                resultListener?.onFailed(error)
            }

            override fun onCompleted() {
                resultListener?.onCompleted()
            }
        })

        resultListener?.onStarted()

        return disposable
    }

    override fun findAll(resultListener: BaseUsecaseListener.OnResultItemListener<List<GiphyGif>>?): Disposable {
        val disposable = gifFavoriteDatabaseImpl.findAll(object : DatabaseCallback.ResultListener<List<GiphyGifFavoriteEntity>> {
            override fun onSuccess(result: List<GiphyGifFavoriteEntity>) {
                val giphyGifs = result.toGiphyGifs
                _gifs.postValue(giphyGifs)

                resultListener?.onSuccess(giphyGifs)

                addAllFavoriteHashSet(result)
            }

            override fun onFailed(error: String) {
                resultListener?.onFailed(error)
            }

            override fun onCompleted() {
                resultListener?.onCompleted()
            }
        })

        resultListener?.onStarted()

        return disposable
    }

    override fun insertItem(
        giphyGif: GiphyGif,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = gifFavoriteDatabaseImpl.insert(giphyGif.toGiphyGifFavoriteEntity, object : DatabaseCallback.ResultListener<Any?> {
            override fun onSuccess(result: Any?) {
                addFavoriteHashSet(giphyGif.id)

                resultListener?.onSuccess()
            }

            override fun onFailed(error: String) {
                resultListener?.onFailed(error)
            }

            override fun onCompleted() {
                resultListener?.onCompleted()
            }
        })

        resultListener?.onStarted()

        return disposable
    }

    override fun deleteItem(
        id: String,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = gifFavoriteDatabaseImpl.delete(id, object : DatabaseCallback.ResultListener<Any?> {
            override fun onSuccess(result: Any?) {
                removeFavoriteHashSet(id)

                resultListener?.onSuccess()
            }

            override fun onFailed(error: String) {
                resultListener?.onFailed(error)
            }

            override fun onCompleted() {
                resultListener?.onCompleted()
            }
        })

        resultListener?.onStarted()

        return disposable
    }

    override fun setFavorite(
        giphyGif: GiphyGif,
        isFavorite: Boolean,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = if(isFavorite){
            insertItem(giphyGif, resultListener)
        } else{
            deleteItem(giphyGif.id, resultListener)
        }

        resultListener?.onStarted()

        return disposable;
    }

    override fun addAllFavoriteHashSet(entities: List<GiphyGifFavoriteEntity>) {
        if(favoriteIdSet == null){
            favoriteIdSet = HashSet<String>()
        }
        for(entity in entities){
            addFavoriteHashSet(entity.id)
        }
    }

    override fun addFavoriteHashSet(id: String) {
        favoriteIdSet?.add(id)
    }

    override fun removeFavoriteHashSet(id: String) {
        if(favoriteIdSet != null && favoriteIdSet?.contains(id)!!){
            favoriteIdSet?.remove(id)
        }
    }

    override fun isFavoriteHashSet(id: String) : Boolean{
        return favoriteIdSet?.contains(id) ?: false
    }


}