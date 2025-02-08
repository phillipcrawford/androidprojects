package com.example.criminaintent

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
    // Read in the dimensions of the image on disk
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
}