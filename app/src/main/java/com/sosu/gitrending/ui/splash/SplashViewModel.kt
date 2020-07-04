package com.sosu.gitrending.ui.splash

import android.os.Handler
import com.sosu.gitrending.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by hyunsuso on 2020/07/04.
 */
class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashNavigator>(){

    companion object{
        val TAG = SplashViewModel::class.java.simpleName

        private const val DELAY_READY_MS = 1000L
    }

    override fun getName(): String {
        return TAG
    }

    /*
    * app loading during DELAY_READY_MS
    * @memo init handle before app start
    * */
    fun onReadyForAppLoading(){
        Handler().postDelayed({
            getNavigator()?.openMainActivity()
        }, DELAY_READY_MS)
    }
}