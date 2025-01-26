package com.example.criminaintent.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.criminaintent.Crime


@Database(entities = [ Crime::class ], version = 1)
abstract class CrimeDatabase : RoomDatabase() {

}