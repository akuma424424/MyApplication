package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(
    private val activity: Activity,
    private val onPermissionsResult: (Boolean) -> Unit,
) {
    // Actualizado para Android 10+
    private val storagePermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_MEDIA_IMAGES,
        Manifest.permission.READ_MEDIA_VIDEO
    )
    private val requestCode = 100
    
    // Solicitar permisos de almacenamiento
    fun requestStoragePermissions() {
        if (checkStoragePermissions()) {
            onPermissionsResult(true)
        } else {
            ActivityCompat.requestPermissions(activity, storagePermissions, requestCode)
        }
    }
    
    // Comprobar si los permisos están concedidos
    private fun checkStoragePermissions(): Boolean {
        return storagePermissions.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }
    
    // Gestionar los resultados de la solicitud de permisos
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == this.requestCode) {
            val granted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
            onPermissionsResult(granted)
        }
    }
}
