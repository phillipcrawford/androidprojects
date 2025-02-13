package com.example.photogallery.api

interface FlickrApi {
    @GET("/")
    suspend fun fetchContents(): String
}