package com.example.criminaintent.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
    suspend fun getCrimes(): List<Crime>
}