package com.example.coronatracker.data

import androidx.lifecycle.LiveData
import com.example.coronatracker.data.db.WorldStat
import com.example.coronatracker.data.network.ApiServices
import com.example.coronatracker.data.network.SafeApiRequest
import javax.inject.Inject

class HomeRepository @Inject constructor(
    val apiServices: ApiServices
) : SafeApiRequest() {

     suspend fun getWorldStat() : WorldStat {
        return apiRequest {
            apiServices.getWorldStat()
        }
    }
}