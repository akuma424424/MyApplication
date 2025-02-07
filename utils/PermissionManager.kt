import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity // Importación necesaria
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.activity.result.registerForActivityResult
import androidx.core.content.ContextCompat

class PermissionManager(
    private val activity: ComponentActivity, // Constructor con 'activity' definido
    private val onPermissionsResult: (Boolean) -> Unit,
) {
    
    private val requestPermissionLauncher =
        activity.registerForActivityResult( // 'activity' se usa aquí correctamente
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val granted = permissions.entries.all { it.value }
            onPermissionsResult(granted)
        }
    
    fun requestStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissions = arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
            if (permissions.all {
                    ContextCompat.checkSelfPermission(
                        activity, // 'activity' se usa aquí correctamente
                        it
                    ) == PackageManager.PERMISSION_GRANTED
                }) {
                // Permisos ya concedidos
                onPermissionsResult(true)
            } else {
                // Solicitar permisos
                requestPermissionLauncher.launch(permissions)
            }
        } else {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (permissions.all {
                    ContextCompat.checkSelfPermission(
                        activity, // 'activity' se usa aquí correctamente
                        it
                    ) == PackageManager.PERMISSION_GRANTED
                }) {
                // Permisos ya concedidos
                onPermissionsResult(true)
            } else {
                // Solicitar permisos
                requestPermissionLauncher.launch(permissions)
            }
        }
    }
}