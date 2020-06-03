package com.example.coronatracker.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface WorldStatDao {
    @Query("SELECT * FROM WorldStat")
    fun getWorldStat(): WorldStat
}