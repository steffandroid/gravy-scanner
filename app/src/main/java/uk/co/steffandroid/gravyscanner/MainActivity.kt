package uk.co.steffandroid.gravyscanner

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        val naughtyApps = resources.openRawResource(R.raw.naughty_apps).reader().use { reader ->
            reader.readLines()
        }

        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { naughtyApps.contains(it.packageName) }
            .sortedBy { it.packageName }
            .map { applicationInfo ->
                InstalledApp(
                    name = packageManager.getApplicationLabel(applicationInfo).toString(),
                    packageName = applicationInfo.packageName
                )
            }

        setContent {
            MaterialTheme {
                MainView(
                    installedApps = installedApps,
                    onItemClicked = { packageName ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            setData(Uri.fromParts("package", packageName, null))
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}
