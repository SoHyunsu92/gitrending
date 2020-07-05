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
        const val EXTRA_SELECT_IDX = "select_idx"
    }

    override fun openMainActivity() : Intent{
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        return intent
    }

    override fun openGiphyDetailActivity(selectIdx : Int): Intent {
        val intent = Intent(context, GiphyDetailActivity::class.java)

        intent.putExtra(EXTRA_SELECT_IDX, selectIdx)

        return intent
    }
}