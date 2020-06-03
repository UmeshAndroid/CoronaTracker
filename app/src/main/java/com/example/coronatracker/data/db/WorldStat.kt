package com.example.coronatracker.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class WorldStat(
    @PrimaryKey var id: Int = 0,
    @SerializedName("total_cases")  val totalCases: String = "1,205,801",
    @SerializedName("total_deaths")  val totalDeaths: String = "64,973",
    @SerializedName("total_recovered")  val totalRecovered: String = "247,961",
    @SerializedName("new_cases")  val newCases: String = "4,348",
    @SerializedName("new_deaths")  val newDeaths: String = "285",
    @SerializedName("statistic_taken_at")  val statisticTakenAt: String = "2020-04-05 09:27:06"
    )