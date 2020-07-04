package com.sosu.gitrending.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.sosu.gitrending.data.model.app.DLog
import java.util.*

/**
 * Created by hyunsuso on 2020/07/04.
 */
object GsonUtils {

    private val TAG = GsonUtils::class.java.simpleName

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS"

    // base
    private fun getBuilder(): GsonBuilder {
        return GsonBuilder().setDateFormat(DATE_FORMAT)
    }
    private fun getGson(): Gson {
        return getBuilder().create()
    }

    // obj -> json string
    fun <T> objectToJsonString(obj: T): String {
        try {
            return getGson().toJson(obj)
        } catch (e: Exception) {
            DLog.e(TAG, e)
        }
        return ""
    }

    // json string -> class
    fun <T> jsonStringToObject(json: String, clazz: Class<T>): T? {
        try {
            return getGson().fromJson(json, clazz)
        } catch (e: Exception) {
            DLog.e(TAG, e)
        }
        return null
    }

    // json string -> class list
    fun <E> jsonStringToObjectList(json: String, clazz: Class<Array<E>>): List<E>? {
        val result = ArrayList<E>()
        try {
            val arr: Array<E> = getGson().fromJson(json, clazz)
            result.addAll(Arrays.asList(*arr))
        } catch (e: JsonSyntaxException) {
            DLog.e(TAG, e)
        }
        return result
    }
}