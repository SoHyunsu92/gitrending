package com.sosu.gitrending.data.model.giphy

import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant.DATA_bitly_url
import com.sosu.gitrending.data.DataConstant.DATA_content_url
import com.sosu.gitrending.data.DataConstant.DATA_create_datetime
import com.sosu.gitrending.data.DataConstant.DATA_embed_url
import com.sosu.gitrending.data.DataConstant.DATA_id
import com.sosu.gitrending.data.DataConstant.DATA_images
import com.sosu.gitrending.data.DataConstant.DATA_import_datetime
import com.sosu.gitrending.data.DataConstant.DATA_rating
import com.sosu.gitrending.data.DataConstant.DATA_slug
import com.sosu.gitrending.data.DataConstant.DATA_source
import com.sosu.gitrending.data.DataConstant.DATA_source_post_url
import com.sosu.gitrending.data.DataConstant.DATA_source_tld
import com.sosu.gitrending.data.DataConstant.DATA_title
import com.sosu.gitrending.data.DataConstant.DATA_trending_datetime
import com.sosu.gitrending.data.DataConstant.DATA_type
import com.sosu.gitrending.data.DataConstant.DATA_update_datetime
import com.sosu.gitrending.data.DataConstant.DATA_url
import com.sosu.gitrending.data.DataConstant.DATA_user
import com.sosu.gitrending.data.DataConstant.DATA_username
import com.sosu.gitrending.data.model.user.User

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class GiphyGif(
    @SerializedName(DATA_type)                  var type : String? = "",
    @SerializedName(DATA_id)                    var id : String? = "",
    @SerializedName(DATA_slug)                  var slug : String? = "",
    @SerializedName(DATA_url)                   var url : String? = "",
    @SerializedName(DATA_bitly_url)             var bitlyUrl: String? = "",
    @SerializedName(DATA_embed_url)             var embedUrl : String? = "",
    @SerializedName(DATA_username)              var username : String? = "",
    @SerializedName(DATA_source)                var source : String? = "",
    @SerializedName(DATA_rating)                var rating : String? = "",
    @SerializedName(DATA_content_url)           var contentUrl : String? = "",
    @SerializedName(DATA_user)                  var user : User? = null,
    @SerializedName(DATA_source_tld)            var sourceTld : String? = "",
    @SerializedName(DATA_source_post_url)       var sourcePostUrl : String? = "",
    @SerializedName(DATA_update_datetime)       var updateDatetime : String? = "",
    @SerializedName(DATA_create_datetime)       var createDatetime : String? = "",
    @SerializedName(DATA_import_datetime)       var importDatetime : String? = "",
    @SerializedName(DATA_trending_datetime)     var trendingDatetime : String? = "",
    @SerializedName(DATA_images)                var images : GiphyImage? = null,
    @SerializedName(DATA_title)                 var title : String? = ""


    ){

    companion object{
        // type default
        private const val TYPE_GIF = "gif"
    }

}