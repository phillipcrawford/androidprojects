package com.example.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Cabcdefrime #$i",
                date = Date(),
                isSolved = i % 2 == 0
            )
            crimes += crime
        }
    }
}