package com.example.photogallery.api

@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)
