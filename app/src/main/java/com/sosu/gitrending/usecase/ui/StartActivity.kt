package com.sosu.gitrending.usecase.ui

import android.content.Intent
import com.sosu.gitrending.data.model.giphy.GiphyGif

/**
 * Created by hyunsuso on 2020/07/04.
 */
interface StartActivity {

    fun openMainActivity() : Intent
    fun openGiphyDetailActivity(giphyGif: GiphyGif) : Intent
}