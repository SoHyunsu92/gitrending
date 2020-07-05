package com.sosu.gitrending.usecase.giphy

import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.usecase.base.BaseUsecaseListener
import io.reactivex.disposables.Disposable

/**
 * Created by hyunsuso on 2020/07/05.
 */
interface GiphyGifsFavoriteRepo{
    /*
        * database item
        * */
    fun findOne(
        id: String,
        resultListener: BaseUsecaseListener.OnResultItemListener<GiphyGif>?
    ): Disposable

    fun findAll(
        resultListener: BaseUsecaseListener.OnResultItemListener<List<GiphyGif>>?
    ): Disposable

    fun insertItem(
        giphyGif: GiphyGif,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable

    fun deleteItem(
        id : String,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable

    /*
    * database favorite
    * */
    fun setFavorite(
        giphyGif: GiphyGif,
        isFavorite : Boolean,
        resultListener: BaseUsecaseListener.OnResultListener?
    ): Disposable

    /*
    * cache favorite id
    * */
    fun addAllFavoriteHashSet(entities: List<GiphyGifFavoriteEntity>)
    fun addFavoriteHashSet(id : String)
    fun removeFavoriteHashSet(id : String)
    fun isFavoriteHashSet(id : String) : Boolean
}