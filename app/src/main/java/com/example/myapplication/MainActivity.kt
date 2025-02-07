import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var permissionManager: PermissionManager // Correct declaration (only once)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Replace with your layout
        
        permissionManager = PermissionManager(this) { granted ->
            if (granted) {
                Log.d("MainActivity", "Permisos concedidos")
                // Permissions granted, proceed with media access
            } else {
                Log.d("MainActivity", "Permisos denegados")
                // Permissions denied, handle accordingly
            }
        }
        
        solicitarPermisos() // Call the function to request permissions
    }
    
    private fun solicitarPermisos() {
        permissionManager.requestStoragePermissions() // Correct call
        // No duplicate declaration here!
    }
}
