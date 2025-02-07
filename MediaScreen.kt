package com.example.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.model.MediaFile
import com.example.myapplication.viewmodel.MediaViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.geometry.isEmpty

@Composable
fun MediaScreen(mediaViewModel: MediaViewModel) {
    val mediaFiles by mediaViewModel.mediaFiles.collectAsState()
    
    if (mediaFiles.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        MediaGrid(mediaFiles = mediaFiles)
    }
}

@Composable
fun MediaGrid(mediaFiles: List<MediaFile>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        items(mediaFiles) { mediaFile ->
            MediaItem(mediaFile = mediaFile)
        }
    }
}

@Composable
fun MediaItem(mediaFile: MediaFile) {
    Image(
        painter = rememberAsyncImagePainter(model = mediaFile.uri),
        contentDescription = mediaFile.name,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
}