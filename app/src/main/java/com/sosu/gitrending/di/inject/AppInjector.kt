package com.sosu.gitrending.di.inject

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.sosu.gitrending.BaseApplication
import com.sosu.gitrending.di.component.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

/**
 * Created by hyunsuso on 2020/07/04.
 */
object AppInjector {

    fun init(application: BaseApplication){
        // inject application
        DaggerAppComponent.builder()
            .applicationBind(application)
            .build()
            .inject(application)

        // activity life cycle function callback
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) { }

            override fun onActivityResumed(activity: Activity) { }

            override fun onActivityPaused(activity: Activity) { }

            override fun onActivityStopped(activity: Activity) { }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) { }

            override fun onActivityDestroyed(activity: Activity) { }
        })
    }

    // inject activity or fragment
    // activity onCreated 시 호출
    private fun handleActivity(activity: Activity){
        if(activity is HasAndroidInjector){
            AndroidInjection.inject(activity)
        }

        // fragment life cycle function callback
        (activity as? FragmentActivity)?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks(){

                // fragment created 시 injectable 체크 후 inject
                override fun onFragmentCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    if(f is Injectable){
                        AndroidSupportInjection.inject(f)
                    }
                }
            }, true)
    }
}