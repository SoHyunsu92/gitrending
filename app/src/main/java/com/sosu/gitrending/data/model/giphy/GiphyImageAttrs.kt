package com.sosu.gitrending.data.model.giphy

import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class GiphyImageAttrs(
    @SerializedName(DataConstant.DATA_width)         var width : Int? = 0,
    @SerializedName(DataConstant.DATA_height)        var height : Int? = 0,
    @SerializedName(DataConstant.DATA_mp4)           var mp4 : String? = "",
    @SerializedName(DataConstant.DATA_webp)          var webp : String? = "",
    @SerializedName(DataConstant.DATA_url)           var url : String? = ""
){
    fun getResUrl() : String? {
        if(url != null && url!!.isNotEmpty()){
            return url
        } else if(webp != null && webp!!.isNotEmpty()){
            return webp
        } else if(mp4 != null && mp4!!.isNotEmpty()){
            return mp4
        }
        return ""
    }
}