package com.sosu.gitrending.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sosu.gitrending.data.local.AppDatabase.Companion.VERSION
import com.sosu.gitrending.data.local.db.dao.GiphyGifFavoriteDao
import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity

/**
 * Created by hyunsuso on 2020/07/05.
 */
@Database(entities = arrayOf(GiphyGifFavoriteEntity::class), version = VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    companion object{
        const val VERSION = 1
        const val DATABASE_NAME = "giterndingDB"
    }

    // dao
    abstract fun giphyGifFavoriteDao() : GiphyGifFavoriteDao

}