package com.example.myapplication.model

import android.net.Uri

data class MediaFile(
    val id: Long,
    val uri: Uri,
    val name: String,
    val type: MediaType,
    val dateAdded: Long,
    val size: Long
)

enum class MediaType {
    IMAGE, VIDEO
}