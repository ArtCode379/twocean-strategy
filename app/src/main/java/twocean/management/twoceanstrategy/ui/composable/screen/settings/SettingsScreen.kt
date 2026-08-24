package twocean.management.twoceanstrategy.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    SettingsScreenContent(modifier)
}

@Composable
fun SettingsScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Text("ABOUT", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Icon(Icons.Outlined.Business, null, tint = MaterialTheme.colorScheme.primary)
                Text("TW OCEAN LTD", style = MaterialTheme.typography.titleMedium)
                Text("Management consulting, strategy development, and organisational transformation.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Icon(Icons.Outlined.Verified, null, tint = MaterialTheme.colorScheme.primary)
                Text("App version 1.0.0")
            }
        }
        Text("CUSTOMER SUPPORT", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
        Button(
            onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twocean.surf")))
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Outlined.Language, null)
            Text("  Visit company website")
        }
    }
}
