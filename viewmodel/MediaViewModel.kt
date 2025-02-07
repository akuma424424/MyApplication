package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.MediaFile
import com.example.myapplication.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MediaViewModel(private val mediaRepository: MediaRepository) : ViewModel() {
    
    private val _mediaFiles = MutableStateFlow<List<MediaFile>>(java.util.Collections.emptyList())
    val mediaFiles: StateFlow<List<MediaFile>> = _mediaFiles
    
    fun loadMediaFiles() {
        viewModelScope.launch {
            val files = mediaRepository.getMediaFiles()
            _mediaFiles.value = files
        }
    }
}