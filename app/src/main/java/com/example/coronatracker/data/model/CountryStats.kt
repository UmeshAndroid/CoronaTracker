package com.example.coronatracker.data.model

import com.google.gson.annotations.SerializedName

data class CountryStats(
    @SerializedName("country_name") val country: String,
    @SerializedName("cases") val totalCases: String,
    @SerializedName("deaths") val deaths: String,
    @SerializedName("total_recovered") val totalRecovered: String,
    @SerializedName("new_deaths") val newDeaths: String,
    @SerializedName("new_cases") val newCases: String,
    @SerializedName("serious_critical") val seriousCritical: String,
    @SerializedName("total_cases_per_1m_population") val totalCasesPer_1M_Population: String,
    @SerializedName("deaths_per_1m_population") val totalDeathsPer_1M_Population: String,
    @SerializedName("total_tests") val totalTests: String,
    @SerializedName("tests_per_1m_population") val totalTestsPer_1M_Population: String
)