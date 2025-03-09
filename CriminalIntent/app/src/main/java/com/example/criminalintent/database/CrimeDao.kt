package com.example.criminalintent.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CrimeDao {
    @Query("SELECT * FROM crime")
}