package com.example.coronatracker.data.model

import com.google.gson.annotations.SerializedName

data class CountryListStats(
   @SerializedName("countries_stat") val countryStats: List<CountryStats>
) {
}