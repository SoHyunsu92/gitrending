package com.sosu.gitrending.data.model.user

import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant.DATA_avatar_url
import com.sosu.gitrending.data.DataConstant.DATA_banner_url
import com.sosu.gitrending.data.DataConstant.DATA_display_name
import com.sosu.gitrending.data.DataConstant.DATA_profile_url
import com.sosu.gitrending.data.DataConstant.DATA_username

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class User(
    @SerializedName(DATA_avatar_url)                    var avatarUrl : String? = "",
    @SerializedName(DATA_banner_url)                    var bannerUrl : String? = "",
    @SerializedName(DATA_profile_url)                   var profileUrl : String? = "",
    @SerializedName(DATA_username)                      var username : String? = "",
    @SerializedName(DATA_display_name)                  var displayName : String? = ""
) {

}