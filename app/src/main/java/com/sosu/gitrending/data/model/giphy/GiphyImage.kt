package com.sosu.gitrending.data.model.giphy

import com.google.gson.annotations.SerializedName
import com.sosu.gitrending.data.DataConstant.DATA_fixed_width
import com.sosu.gitrending.data.DataConstant.DATA_original
import com.sosu.gitrending.data.DataConstant.DATA_preview_gif

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class GiphyImage(
    @SerializedName(DATA_original)              var original : GiphyImageAttrs? = null,
    @SerializedName(DATA_preview_gif)           var previewGif : GiphyImageAttrs? = null,
    @SerializedName(DATA_fixed_width)           var fixedWidth : GiphyImageAttrs? = null
){

}