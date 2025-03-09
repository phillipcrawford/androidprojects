package com.example.criminalintent

import android.content.Context
import androidx.room.Room
import com.example.criminalintent.database.CrimeDatabase

class CrimeRepository private constructor(context: Context) {

    private val database: CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        )

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}