package com.sosu.gitrending.data.local.db.impl.giphy

import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity
import com.sosu.gitrending.data.local.db.impl.base.DatabaseCallback
import io.reactivex.disposables.Disposable

/**
 * Created by hyunsuso on 2020/07/05.
 */
interface GiphyGifFavoriteDatabase {

    fun findOne(
        id : String,
        resultListener: DatabaseCallback.ResultListener<GiphyGifFavoriteEntity>
    ) : Disposable

    fun findAll(
        resultListener: DatabaseCallback.ResultListener<List<GiphyGifFavoriteEntity>>
    ) : Disposable

    fun insert(
        entity : GiphyGifFavoriteEntity,
        resultListener: DatabaseCallback.ResultListener<Any?>
    ) : Disposable

    fun delete(
        id : String,
        resultListener: DatabaseCallback.ResultListener<Any?>
    ) : Disposable
}