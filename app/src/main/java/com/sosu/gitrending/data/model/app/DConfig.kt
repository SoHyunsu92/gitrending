package com.sosu.gitrending.data.model.app

import android.content.Context
import android.content.pm.PackageInfo
import androidx.databinding.library.BuildConfig

/**
 * Created by hyunsuso on 2020/07/04.
 */
object DConfig {

    val TAG = DConfig::class.java.simpleName

    enum class Deploy {
        DEVELOP,
        STAGING,
        RELEASE
    }

    private lateinit var appInfo: AppInfo
    private var isDebugMode = true;

    private val releaseType =
        Deploy.DEVELOP
    // Deploy.STAGING
    // Deploy.RELEASE

    fun init(context: Context){
        initAppInfo(context)

        when(releaseType){
            Deploy.DEVELOP -> {
                isDebugMode = true
            }

            Deploy.STAGING ->{
                isDebugMode = true
            }

            Deploy.RELEASE -> {
                isDebugMode = false
            }
        }
    }

    private fun initAppInfo(context: Context){
        val pkg = context.packageName

        val packageManager = context.packageManager
        val packageInfo: PackageInfo = packageManager.getPackageInfo(pkg, 0)

        val appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString().toLowerCase()

        appInfo = AppInfo(pkg = pkg, name = appName, ver = BuildConfig.VERSION_NAME, verCode = BuildConfig.VERSION_CODE)
    }

    fun getAppInfo(): AppInfo{
        return appInfo
    }

    fun isDebugMode(): Boolean{
        return isDebugMode
    }
}