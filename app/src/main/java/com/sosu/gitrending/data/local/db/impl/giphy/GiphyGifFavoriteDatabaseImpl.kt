package com.sosu.gitrending.data.local.db.impl.giphy

import com.sosu.gitrending.data.local.AppDatabase
import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity
import com.sosu.gitrending.data.local.db.impl.base.DatabaseCallback
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
class GiphyGifFavoriteDatabaseImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : GiphyGifFavoriteDatabase {

    private val giphyGifFavoriteDao = appDatabase.giphyGifFavoriteDao()

    override fun findOne(
        id: String,
        resultListener: DatabaseCallback.ResultListener<GiphyGifFavoriteEntity>
    ): Disposable {
        return Observable.fromCallable<GiphyGifFavoriteEntity>(Callable<GiphyGifFavoriteEntity> {
            this.giphyGifFavoriteDao.findOne(id)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(DatabaseCallback.DBObservable(resultListener = resultListener))
    }

    override fun findAll(
        resultListener: DatabaseCallback.ResultListener<List<GiphyGifFavoriteEntity>>
    ): Disposable {
        return Observable.fromCallable<List<GiphyGifFavoriteEntity>>(Callable<List<GiphyGifFavoriteEntity>> {
            this.giphyGifFavoriteDao.findAll()
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(DatabaseCallback.DBObservable(resultListener = resultListener))
    }

    override fun insert(
        entity: GiphyGifFavoriteEntity,
        resultListener: DatabaseCallback.ResultListener<Any?>
    ): Disposable {
        return Observable.fromCallable<Any?>(Callable<Any?> {
            this.giphyGifFavoriteDao.insert(entity)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(DatabaseCallback.DBObservable(resultListener = resultListener))
    }

    override fun delete(
        id: String,
        resultListener: DatabaseCallback.ResultListener<Any?>
    ): Disposable {
        return Observable.fromCallable<Any?>(Callable<Any?> {
            this.giphyGifFavoriteDao.delete(GiphyGifFavoriteEntity(id = id))
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(DatabaseCallback.DBObservable(resultListener = resultListener))
    }
}