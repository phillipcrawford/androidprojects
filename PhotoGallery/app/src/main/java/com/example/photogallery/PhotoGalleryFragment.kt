package com.example.photogallery

import androidx.fragment.app.Fragment
import com.example.photogallery.databinding.FragmentPhotoGalleryBinding

class PhotoGalleryFragment {
    class PhotoGalleryFragment : Fragment(){
        private var _binding: FragmentPhotoGalleryBinding? = null
        private var binding
            get() = checkNotNull(_binding) {
                "Cannot access binding because it is null. Is the view visible?"
            }
    }
}