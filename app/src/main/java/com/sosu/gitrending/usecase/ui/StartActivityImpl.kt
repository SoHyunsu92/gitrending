package com.sosu.gitrending.usecase.ui

import android.content.Context
import android.content.Intent
import com.sosu.gitrending.data.model.giphy.GiphyGif
import com.sosu.gitrending.ui.giphy.detail.GiphyDetailActivity
import com.sosu.gitrending.ui.main.MainActivity
import com.sosu.gitrending.utils.GsonUtils
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by hyunsuso on 2020/07/04.
 */
@Singleton
class StartActivityImpl @Inject constructor(
    private val context: Context
) : StartActivity{

    companion object{
        const val REQUEST_CODE_GIPHY_DETAIL = 3001

        const val RESULT_CODE_REFRESH_FAVORITE = 300

        const val EXTRA_GIPHY_GIF = "giphy_gif"
    }

    override fun openMainActivity() : Intent{
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        return intent
    }

    override fun openGiphyDetailActivity(giphyGif: GiphyGif): Intent {
        val intent = Intent(context, GiphyDetailActivity::class.java)

        intent.putExtra(EXTRA_GIPHY_GIF, GsonUtils.objectToJsonString(giphyGif))

        return intent
    }
}