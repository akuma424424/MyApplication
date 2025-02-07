package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.MediaRepository
import com.example.myapplication.viewmodel.MediaViewModel

class MediaViewModelFactory(private val mediaRepository: MediaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MediaViewModel::class.java)) {
            return MediaViewModel(mediaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}