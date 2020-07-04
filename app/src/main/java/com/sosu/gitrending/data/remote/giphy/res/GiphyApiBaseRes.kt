package com.sosu.gitrending.data.remote.giphy.res

import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant.DATA_count
import com.sosu.gitrending.data.DataConstant.DATA_data
import com.sosu.gitrending.data.DataConstant.DATA_meta
import com.sosu.gitrending.data.DataConstant.DATA_msg
import com.sosu.gitrending.data.DataConstant.DATA_offset
import com.sosu.gitrending.data.DataConstant.DATA_pagination
import com.sosu.gitrending.data.DataConstant.DATA_response_id
import com.sosu.gitrending.data.DataConstant.DATA_status
import com.sosu.gitrending.data.DataConstant.DATA_total_count
import com.sosu.gitrending.data.remote.base.error.NetworkError
import com.sosu.gitrending.utils.GsonUtils

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class GiphyApiBaseRes<T>(
    @SerializedName(DATA_meta)                  var meta : Meta?,
    @SerializedName(DATA_data)                  var data : T?,
    @SerializedName(DATA_pagination)            var pagination : Pagination?
){

    data class Meta(
        @SerializedName(DATA_msg)                  var msg : String? = "",
        @SerializedName(DATA_status)               var code : Int? = 0,
        @SerializedName(DATA_response_id)          var resId : String? = ""
    )

    data class Pagination(
        @SerializedName(DATA_offset)               var offset : Int? = 0,
        @SerializedName(DATA_total_count)          var totalCount : Int? = 0,
        @SerializedName(DATA_count)                var count : Int? = 0
    )
    fun isError(): Boolean{
        return !NetworkError.isHttpSuccess(meta?.code ?: 0)
    }

    fun getError(): String{
        return meta?.msg ?: "Error"
    }

    override fun toString(): String {
        return GsonUtils.objectToJsonString(this)
    }

}