package com.example.criminaintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        Log.d(TAG, "init starting")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched")
            delay(5000)
            for (i in 0 until 100) {
                val crime = Crime(
                    id = UUID.randomUUID(),
                    title = "Crime #$i",
                    date = Date(),
                    isSolved = i % 2 == 0
                )

                crimes += crime
            }
            Log.d(TAG, "Loading crimes finished")
        }
    }
}