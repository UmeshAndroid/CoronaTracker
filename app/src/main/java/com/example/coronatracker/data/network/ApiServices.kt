package com.example.coronatracker.data.network

import com.example.coronatracker.data.db.WorldStat
import com.example.coronatracker.data.model.CountryListStats
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("/coronavirus/worldstat.php")
    suspend fun getWorldStat():Response<WorldStat>

    @GET("/coronavirus/cases_by_country.php")
    suspend fun getCountryWiseStats():Response<CountryListStats>
}