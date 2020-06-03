package com.example.coronatracker.data.network

class CommonException(val apiError: List<Error>) : Throwable() {
    constructor(errorType: ErrorType, errorCode: String? = "", errorResponseCode: String? = null):
            this(listOf(CommonException.Error(errorType, errorCode, errorResponseCode)))


    enum class ErrorType(val type: String){
        AUTH_ERROR("AUTH_ERROR"), ERROR("ERROR"), WARNING("WARNING"), ERROR_CODE_CONNECTION("CONNECTION_ERROR")
    }

    class Error(val errorType: ErrorType, val errorCode: String?, val errorResponseCode: String?= "")

    companion object {
        const  val ERROR_CODE_CONNECTION = "connection_error"
        val connectionError = CommonException(CommonException.ErrorType.ERROR_CODE_CONNECTION, "connection_error")
    }

}