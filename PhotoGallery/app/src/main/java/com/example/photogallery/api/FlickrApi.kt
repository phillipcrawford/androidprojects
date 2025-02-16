package com.example.photogallery.api

import retrofit2.http.GET

interface FlickrApi {
    @GET(
        
    )
    suspend fun fetchPhotos():String
}