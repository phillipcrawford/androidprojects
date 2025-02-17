package com.example.photogallery

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photogallery.api.GalleryItem
import com.example.photogallery.databinding.ListItemGalleryBinding

class PhotoViewHolder (
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: GalleryItem) {
        //TODO
    }
}

class PhotoListAdapter(
    private val galleryItems: List<GalleryItem>
) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {

    }
}