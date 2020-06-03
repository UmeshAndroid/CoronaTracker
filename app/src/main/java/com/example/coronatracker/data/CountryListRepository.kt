package com.example.coronatracker.data

import com.example.coronatracker.data.model.CountryListStats
import com.example.coronatracker.data.network.ApiServices
import com.example.coronatracker.data.network.SafeApiRequest
import javax.inject.Inject

class CountryListRepository @Inject constructor(
    val apiServices: ApiServices
) : SafeApiRequest() {
    suspend fun getCountryWiseStats() : CountryListStats{
        return apiRequest{
            apiServices.getCountryWiseStats()
        }
    }
}