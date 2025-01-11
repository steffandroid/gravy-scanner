package uk.co.steffandroid.gravyscanner

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val naughtyApps = resources.openRawResource(R.raw.naughty_apps).reader().use { reader ->
            reader.readLines()
        }

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.statusBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
                        .filter { naughtyApps.contains(it.packageName) }
                        .sortedBy { it.packageName }
                        .forEach { applicationInfo ->
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .clickable {
                                        val intent =
                                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                setData(
                                                    Uri.fromParts(
                                                        "package",
                                                        applicationInfo.packageName,
                                                        null
                                                    )
                                                )
                                            }
                                        startActivity(intent)
                                    }
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = packageManager.getApplicationLabel(applicationInfo)
                                        .toString(),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = applicationInfo.packageName,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                }
            }
        }
    }
}
