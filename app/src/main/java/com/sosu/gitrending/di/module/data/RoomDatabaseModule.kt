package com.sosu.gitrending.di.module.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sosu.gitrending.data.local.AppDatabase
import com.sosu.gitrending.data.local.AppDatabase.Companion.DATABASE_NAME
import com.sosu.gitrending.data.local.db.impl.giphy.GiphyGifFavoriteDatabaseImpl
import com.sosu.gitrending.usecase.thread.AppExecutorsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/05.
 */
@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDB(context: Context, appExecutorsImpl: AppExecutorsImpl): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .addCallback(object: RoomDatabase.Callback(){
                // prepopulate the database after onCreate was called
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    // @memo insert db into session on io thread
                    appExecutorsImpl.diskIO().execute{

                    }
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideGiphyGifFavoriteDatabaseImpl(appDatabase: AppDatabase): GiphyGifFavoriteDatabaseImpl {
        return GiphyGifFavoriteDatabaseImpl(
            appDatabase
        )
    }
}