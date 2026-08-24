package twocean.management.twoceanstrategy.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.data.model.ServiceModel
import twocean.management.twoceanstrategy.ui.composable.shared.HRPIKContentWrapper
import twocean.management.twoceanstrategy.ui.state.DataUiState
import twocean.management.twoceanstrategy.ui.viewmodel.ServiceDetailsViewModel
import java.time.format.DateTimeFormatter

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val state by viewModel.serviceState.collectAsState()
    LaunchedEffect(serviceId) {
        viewModel.observeServiceById(serviceId)
    }
    HRPIKContentWrapper(
        dataState = state,
        dataPopulated = {
            ServiceDetails((state as DataUiState.Populated).data, modifier, onNavigateToCheckout)
        },
        dataEmpty = {
            Text("Service details are unavailable.", Modifier.padding(24.dp))
        },
    )
}

@Composable
private fun ServiceDetails(service: ServiceModel, modifier: Modifier, onBook: (Int) -> Unit) {
    var selectedSlot by remember { mutableStateOf(service.availableTime?.firstOrNull()) }
    LazyColumn(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        item {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                contentScale = ContentScale.Crop,
            )
        }
        item {
            Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(service.name, style = MaterialTheme.typography.titleLarge)
                Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                    Text(service.category, Modifier.padding(horizontal = 12.dp, vertical = 6.dp), color = MaterialTheme.colorScheme.primary)
                }
                Text("From £${service.price.toInt()} · ${service.durationMinutes} min", style = MaterialTheme.typography.titleMedium)
                Text(service.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        item {
            Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("What you will receive", style = MaterialTheme.typography.titleMedium)
                service.features.forEach { feature ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.CheckCircle, null, tint = MaterialTheme.colorScheme.primary)
                        Text(feature)
                    }
                }
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Available times", Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.titleMedium)
                LazyRow(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(service.availableTime.orEmpty()) { time ->
                        AssistChip(
                            onClick = { selectedSlot = time },
                            label = {
                                val suffix = if (selectedSlot == time) " · selected" else ""
                                Text(time.format(DateTimeFormatter.ofPattern("HH:mm")) + suffix)
                            },
                        )
                    }
                }
            }
        }
        item {
            Button(
                onClick = { onBook(service.id) },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(52.dp),
            ) {
                Text("Book Consultation")
            }
        }
    }
}
