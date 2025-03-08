package com.example.criminalintent.database

import androidx.room.Database

@Database(entities = [ Crime::class ], version = 1)
abstract class CrimeDatabase {
}