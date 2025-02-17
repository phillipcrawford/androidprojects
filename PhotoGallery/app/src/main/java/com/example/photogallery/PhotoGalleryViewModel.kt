package com.example.photogallery

import androidx.lifecycle.ViewModel

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel : ViewModel {
    private val photoRepository = PhotoRepository()
}