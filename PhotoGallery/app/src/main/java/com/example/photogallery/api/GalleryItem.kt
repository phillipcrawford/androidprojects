package com.example.photogallery.api

@JsonClass(generateAdapter = true)
data class GalleryItem(
    val title: String,
    val id: String,
    val url: String
)
