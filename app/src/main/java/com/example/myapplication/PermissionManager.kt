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
    private val storagePermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val requestCode = 100
    
    fun requestStoragePermissions() {
        if (checkStoragePermissions()) {
            onPermissionsResult(true)
        } else {
            ActivityCompat.requestPermissions(activity, storagePermissions, requestCode)
        }
    }
    
    private fun checkStoragePermissions(): Boolean {
        return storagePermissions.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }
    
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