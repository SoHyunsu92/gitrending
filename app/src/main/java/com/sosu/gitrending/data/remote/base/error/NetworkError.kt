package com.sosu.gitrending.data.remote.base.error

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

/**
 * Created by hyunsuso on 2020/07/04.
 */
data class NetworkError(
    private val code: Int,
    private val msg: String
){

    val TAG = NetworkError::class.java.simpleName

    companion object{
        /*
         * 1xx : 조건부 응답
         * 2xx : 성공
         * 3xx : 리다이렉션 성공
         * 4xx : 요청 오류
         * 5xx : 서버 오류
         * */
        const val HTTP_STATUS_CODE_1xx = 1
        const val HTTP_STATUS_CODE_2xx = 2
        const val HTTP_STATUS_CODE_3xx = 3
        const val HTTP_STATUS_CODE_4xx = 4
        const val HTTP_STATUS_CODE_5xx = 5

        private const val HTTP_STATUS_CODE_1xx_MSG = "Please try again in a few minutes."
        private const val HTTP_STATUS_CODE_2xx_MSG = "Success"
        private const val HTTP_STATUS_CODE_3xx_MSG = "Success (Redirect)"
        private const val HTTP_STATUS_CODE_4xx_MSG = "Timeout the Internet connection. \n Please try again in a few minutes."
        private const val HTTP_STATUS_CODE_5xx_MSG = "Error has occurred of server. \n Please try again in a few minutes."

        // check http success
        fun isHttpSuccess(code: Int): Boolean {
            when (code / 100) {
                HTTP_STATUS_CODE_1xx -> return false
                HTTP_STATUS_CODE_2xx -> return true
                HTTP_STATUS_CODE_3xx -> return true
                HTTP_STATUS_CODE_4xx -> return false
                HTTP_STATUS_CODE_5xx -> return false
            }
            return false
        }

        fun handleApiError(error: Throwable): NetworkError {
            var code: Int
            var msg: String

            if (error is HttpException) {
                when (error.code()) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        code = HttpsURLConnection.HTTP_UNAUTHORIZED
                        msg = "Unauthorised User"
                    }
                    HttpsURLConnection.HTTP_FORBIDDEN -> {
                        code = HttpsURLConnection.HTTP_FORBIDDEN
                        msg = "Forbidden"
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                        code = HttpsURLConnection.HTTP_INTERNAL_ERROR
                        msg = "Internal Server Error"
                    }
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                        code = HttpsURLConnection.HTTP_BAD_REQUEST
                        msg = "Bad Request"
                    }
                    else -> {
                        code = error.code()
                        msg = error.localizedMessage?: ""
                    }
                }
            } else if (error is JsonSyntaxException) {
                code = 500
                msg = "Something Went Wrong API is not responding properly!"
            } else {
                code = 500
                msg = error.message ?: ""
            }

            return NetworkError(code, msg)
        }
    }

    fun getFullMsg(): String{
        return "$code:$msg"
    }
}