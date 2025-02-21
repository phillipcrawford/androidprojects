package com.example.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
//    @GET(
//        "services/rest/?method=flickr.interestingness.getList" +
//            "&api_key=f59142b24d41487d124b33c63a65bf2f" +
//            "&format=json" +
//            "&nojsoncallback=1" +
//            "&extras=url_s"
//    )
    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse

    @GET("services/rest?method=flickr.photos.search")
    suspend fun searchPhotos(@Query("text") query: String): FlickrResponse
}