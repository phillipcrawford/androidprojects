package com.example.helloworldapp.viewmodel

data class DisplayVendor(
    val vendorName: String,
    val user1Count: Int,
    val user2Count: Int,
    val distanceMiles: Double = 1.2,
    val querySpecificRating: Float = 0f,
    val combinedRelevantItemCount: Int = 0
)