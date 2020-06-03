package com.example.coronatracker.data.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class MockDataProvider(val fileName: String) {

    fun getDataResponse(request: Request): Response {
        val json = readResource(fileName)

        return Response.Builder()
            .code(200)
            .addHeader("Content-Type", "application/json")
            .body(ResponseBody.create("application/json".toMediaTypeOrNull(), json))
            .message(json)
            .request(request)
            .protocol(Protocol.HTTP_2)
            .build()
    }

    fun readResource(resourceName: String): String {
        val fileNameByCountry = "mockdata/$resourceName"
        val classLoader = Thread.currentThread().contextClassLoader
        classLoader.getResourceAsStream(fileNameByCountry).use { return it.reader().readText() }
    }
}