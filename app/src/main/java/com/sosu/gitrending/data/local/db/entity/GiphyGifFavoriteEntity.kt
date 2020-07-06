package com.sosu.gitrending.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant.DATA_id
import com.sosu.gitrending.data.DataConstant.DATA_images
import com.sosu.gitrending.data.DataConstant.DATA_title
import com.sosu.gitrending.data.DataConstant.DATA_type
import com.sosu.gitrending.data.DataConstant.DATA_user
import com.sosu.gitrending.data.local.db.dao.GiphyGifFavoriteDao
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.data.model.giphy.GiphyImage
import com.sosu.gitrending.data.model.user.User
import com.sosu.gitrending.utils.GsonUtils

/**
 * Created by hyunsuso on 2020/07/05.
 *
 * GiphyGif 의 필수 정보만 저장하고 사용한다.
 * 필수 정보란, main list or detail 화면에서 네트워크가 되지 않아도 보여줘야할 정보이다.
 */
@Entity(
    tableName = GiphyGifFavoriteDao.DB_NAME
)
data class GiphyGifFavoriteEntity(

    @PrimaryKey @ColumnInfo(name = DATA_id)         var id : String,
    @SerializedName(DATA_type)                      var type : String? = "",
    @SerializedName(DATA_user)                      var user : String? = "",        // User Object string
    @SerializedName(DATA_images)                    var images : String? = "",      // GiphyImage Object string
    @SerializedName(DATA_title)                     var title : String? = ""

){

    override fun hashCode(): Int {
        return id.hashCode() * 31
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GiphyGifFavoriteEntity

        if (id != other.id) return false

        return true
    }

    override fun toString(): String {
        return GsonUtils.objectToJsonString(this)
    }
}

/*
* extension
* */
var GiphyGifFavoriteEntity.toGiphyGif : GiphyGif
    get() {
        return GiphyGif(
            id = id,
            type = type,
            user = GsonUtils.jsonStringToObject(user ?: "", User::class.java),
            images = GsonUtils.jsonStringToObject(images ?: "", GiphyImage::class.java),
            title = title
        )
    }
    set(value) {}

var List<GiphyGifFavoriteEntity>.toGiphyGifs : List<GiphyGif>
    get() {
        val places = ArrayList<GiphyGif>()

        for(item in this){
            places.add(item.toGiphyGif)
        }
        return places
    }
    set(value) {}