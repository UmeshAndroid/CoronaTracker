package com.example.coronatracker.data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requst = chain.request()

        fullPathSegmentProcessors()[requst.url.encodedPath]?.let {
            return it.invoke(requst)
        }

        throw Exception("Mock data not provided for ${requst.url}")
    }

    private fun fullPathSegmentProcessors(): Map<String, (Request) -> Response> {
        return mapOf(
            WORLD_STAT_URL to { request ->
                MockDataProvider(fileName = "worldStat.json").getDataResponse(request)
            },
            COUNTRY_LIST_STATS_URL to { request ->
                MockDataProvider(fileName = "casesByCountries.json").getDataResponse(request)
            }
        )
    }

    companion object {
        const val WORLD_STAT_URL = "/coronavirus/worldstat.php"
        const val COUNTRY_LIST_STATS_URL = "/coronavirus/cases_by_country.php"
    }
}