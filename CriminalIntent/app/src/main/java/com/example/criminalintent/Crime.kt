package com.example.criminalintent

import androidx.room.Entity
import java.util.Date
import java.util.UUID

@Entity
data class Crime(
    val id: UUID,
    val title: String,
    val date: Date,
    val isSolved: Boolean,
)
