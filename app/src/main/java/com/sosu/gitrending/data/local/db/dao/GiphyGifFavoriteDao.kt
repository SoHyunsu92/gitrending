package com.sosu.gitrending.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.sosu.gitrending.data.DataConstant.DATA_id
import com.sosu.gitrending.data.local.db.entity.GiphyGifFavoriteEntity

/**
 * Created by hyunsuso on 2020/07/05.
 *
 * giphy gif favorite
 * 사용자가 favorite 한 데이터를 관리한다.
 */
@Dao
interface GiphyGifFavoriteDao : BaseDao<GiphyGifFavoriteEntity> {

    companion object{
        const val DB_NAME = "giphy_gif_favorite_v1"
    }

    /*
    * find one favorite giphy gif with id
    * */
    @Query("SELECT * FROM $DB_NAME WHERE $DATA_id LIKE :id LIMIT 1")
    fun findOne(id: String): GiphyGifFavoriteEntity

    /*
    * find all
    * */
    @Query("SELECT * FROM $DB_NAME")
    fun findAll(): List<GiphyGifFavoriteEntity>
}