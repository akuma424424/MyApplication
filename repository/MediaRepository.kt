package com.example.myapplication.repository

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.example.myapplication.model.MediaFile
import com.example.myapplication.model.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaRepository(private val context: Context) {
    
    suspend fun getMediaFiles(): List<MediaFile> = withContext(Dispatchers.IO) {
        val mediaFiles = kotlin.collections.mutableListOf<MediaFile>()
        val imageCollection =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }
        val videoCollection =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }
        
        val projection = arrayOf(
            MediaStore.MediaColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.DATE_ADDED,
            MediaStore.MediaColumns.SIZE
        )
        
        val imageCursor = context.contentResolver.query(
            imageCollection,
            projection,
            null,
            null,
            "${MediaStore.MediaColumns.DATE_ADDED} DESC"
        )
        val videoCursor = context.contentResolver.query(
            videoCollection,
            projection,
            null,
            null,
            "${MediaStore.MediaColumns.DATE_ADDED} DESC"
        )
        
        imageCursor?.use { cursor ->
            mediaFiles.addAll(getMediaFilesFromCursor(cursor, MediaType.IMAGE))
        }
        videoCursor?.use { cursor ->
            mediaFiles.addAll(getMediaFilesFromCursor(cursor, MediaType.VIDEO))
        }
        
        mediaFiles
    }
    
    private fun getMediaFilesFromCursor(cursor: Cursor, mediaType: MediaType): List<MediaFile> {
        val mediaFiles = kotlin.collections.mutableListOf<MediaFile>()
        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
        val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
        val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)
        val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE)
        
        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumn)
            val name = cursor.getString(nameColumn)
            val date