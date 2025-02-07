package com.example.myapplication

import android.content.Context
import android.util.Log

class MediaRepository(private val context: Context) {
    fun getMediaFiles(): List<String> {
        Log.d("MediaRepository", "getMediaFiles")
        // Aquí va la lógica para obtener los archivos multimedia
        return emptyList()
    }
}