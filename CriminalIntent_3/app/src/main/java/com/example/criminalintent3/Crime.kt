package com.example.criminalintent3

import com.android.identity.util.UUID
import java.util.Date

data class Crime(
    val id: UUID,
    val title: String,
    val date: Date,
    val isSolved: Boolean
)
