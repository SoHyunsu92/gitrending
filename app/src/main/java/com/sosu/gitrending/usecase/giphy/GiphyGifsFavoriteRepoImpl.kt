package com.sosu.gitrending.usecase.giphy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity
import com.sosu.gitrending.data.local.db.entity.toGiphyGif
import com.sosu.gitrending.data.local.db.entity.toGiphyGifs
import com.sosu.gitrending.data.local.db.impl.base.DatabaseCallback
import com.sosu.gitrending.data.local.db.impl.giphy.GiphyGifFavoriteDatabaseImpl
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.model.giphy.toGiphyGifFavoriteEntity
import com.sosu.gitrending.usecase.base.BaseUsecaseListener
import io.reactivex.disposables.Disposable
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

    // favorite gifs
    private val _gifs = MutableLiveData<List<GiphyGif>>()
    val gifs: LiveData<List<GiphyGif>>
        get() = _gifs

    // is giphy gif favorite
    // use favorite cache
    private var favoriteIdSet : HashSet<String>? = null

    // 시작시, find all data
    init {
        findAll(null)
    }

    // find one entity
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

    // find all entities
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

    // insert entity
    override fun insertItem(
        giphyGif: GiphyGif,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = gifFavoriteDatabaseImpl.insert(giphyGif.toGiphyGifFavoriteEntity, object : DatabaseCallback.ResultListener<Any?> {
            override fun onSuccess(result: Any?) {
                onAfterInsert(giphyGif)

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

    // delete entity
    override fun deleteItem(
        giphyGif: GiphyGif,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = gifFavoriteDatabaseImpl.delete(giphyGif.id, object : DatabaseCallback.ResultListener<Any?> {
            override fun onSuccess(result: Any?) {
                onAfterDelete(giphyGif)

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

    // set favorite, db operation and hashSet handling
    // true - insert
    // false - delete
    override fun setFavorite(
        giphyGif: GiphyGif,
        isFavorite: Boolean,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable {
        val disposable = if(isFavorite){
            insertItem(giphyGif, resultListener)
        } else{
            deleteItem(giphyGif, resultListener)
        }

        resultListener?.onStarted()

        return disposable;
    }

    // add all hash set
    override fun addAllFavoriteHashSet(entities: List<GiphyGifFavoriteEntity>) {
        if(favoriteIdSet == null){
            favoriteIdSet = HashSet<String>()
        }
        for(entity in entities){
            addFavoriteHashSet(entity.id)
        }
    }

    // add hash set
    // = favorite
    override fun addFavoriteHashSet(id: String) {
        favoriteIdSet?.add(id)
    }

    // remove hash set
    // = un favorite
    override fun removeFavoriteHashSet(id: String) {
        if(favoriteIdSet != null && favoriteIdSet?.contains(id)!!){
            favoriteIdSet?.remove(id)
        }
    }

    // check favorite on hash set
    override fun isFavoriteHashSet(id: String) : Boolean{
        return favoriteIdSet?.contains(id) ?: false
    }

    // handling insert
    private fun onAfterInsert(giphyGif: GiphyGif){
        insertLivaData(giphyGif)

        addFavoriteHashSet(giphyGif.id)
    }

    // handling delete
    private fun onAfterDelete(giphyGif: GiphyGif){
        removeLiveData(giphyGif)

        removeFavoriteHashSet(giphyGif.id)
    }

    // for add live data <List>
    private fun insertLivaData(giphyGif: GiphyGif){
        val items = _gifs.value as ArrayList<GiphyGif> ?: return
        items.add(giphyGif)
        _gifs.value = items
    }

    // for remove live data <List>
    private fun removeLiveData(giphyGif: GiphyGif){
        val items = _gifs.value as ArrayList<GiphyGif> ?: return
        items.remove(giphyGif)
        _gifs.value = items
    }

}