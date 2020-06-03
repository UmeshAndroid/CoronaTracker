package com.example.coronatracker.data.network

import com.example.coronatracker.utils.APIException
import retrofit2.Response

abstract class SafeApiRequest {

     suspend fun<T:Any> apiRequest(call:suspend () -> Response<T>): T{
        val response = call.invoke()

         if(response.isSuccessful){
             return response.body()!!
         }else{

             throw (APIException(response.errorBody().toString()))
         }
    }
}