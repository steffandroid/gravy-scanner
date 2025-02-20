package uk.co.steffandroid.gravyscanner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainView(installedApps: List<InstalledApp>, onItemClicked: (String) -> Unit) {
    Scaffold { padding ->
        LazyColumn(contentPadding = padding) {
            if (installedApps.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.no_apps_found),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp)
                    )
                }
            } else {
                installedApps.forEach { installedApp ->
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .clickable { onItemClicked(installedApp.packageName) }
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = installedApp.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = installedApp.packageName,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainView(
        installedApps = listOf(
            InstalledApp(name = "Test app", packageName = "com.example.test")
        ),
        onItemClicked = {}
    )
}

@Preview
@Composable
fun MainViewEmptyPreview() {
    MainView(installedApps = emptyList(), onItemClicked = {})
}
