package com.example.photogallery

import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.api.GalleryItem
import com.example.photogallery.databinding.ListItemGalleryBinding

class PhotoListAdapter (
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {
        //TODO
    }
}