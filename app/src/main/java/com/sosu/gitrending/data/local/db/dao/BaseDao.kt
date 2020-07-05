package com.sosu.gitrending.data.local.db.dao

import androidx.room.*

/**
 * Created by hyunsuso on 2020/07/05.
 *
 * 기본 Dao
 * onConflict = Replace
 */
@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<T>)

    @Delete
    fun delete(obj: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(obj: T)
}