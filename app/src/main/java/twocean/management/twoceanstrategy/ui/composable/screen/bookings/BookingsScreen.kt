package twocean.management.twoceanstrategy.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.ui.composable.shared.HRPIKContentWrapper
import twocean.management.twoceanstrategy.ui.state.BookingUiState
import twocean.management.twoceanstrategy.ui.state.DataUiState
import twocean.management.twoceanstrategy.ui.theme.Success
import twocean.management.twoceanstrategy.ui.viewmodel.BookingViewModel

@Composable
fun BookingsScreen(modifier: Modifier = Modifier, viewModel: BookingViewModel = koinViewModel()) {
    val state by viewModel.bookingsState.collectAsState()
    var cancelNumber by remember { mutableStateOf<String?>(null) }
    HRPIKContentWrapper(
        dataState = state,
        dataPopulated = {
            BookingsPopulated((state as DataUiState.Populated).data, modifier) { cancelNumber = it }
        },
        dataEmpty = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("No bookings yet", style = MaterialTheme.typography.titleLarge)
                Text("Browse Services from Home to schedule your first strategy session.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
    )
    cancelNumber?.let { number ->
        AlertDialog(
            onDismissRequest = { cancelNumber = null },
            title = { Text("Cancel this booking?") },
            text = { Text("The reserved consultation slot will be released.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(number)
                        cancelNumber = null
                    },
                ) {
                    Text("Cancel booking", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { cancelNumber = null }) {
                    Text("Keep booking")
                }
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier,
    onCancel: (String) -> Unit,
) {
    LazyColumn(
        modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text("Your consultations", style = MaterialTheme.typography.headlineMedium)
        }
        items(bookings, key = { it.bookingNumber }) { booking ->
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(booking.serviceName, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                        Surface(shape = RoundedCornerShape(50), color = Success.copy(alpha = 0.12f)) {
                            Text("Confirmed", Modifier.padding(horizontal = 10.dp, vertical = 5.dp), color = Success)
                        }
                    }
                    Text("Booking #${booking.bookingNumber}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(booking.timestamp)
                    Text("Your consultant will meet you online or at the office at the confirmed time.", style = MaterialTheme.typography.bodyMedium)
                    TextButton(onClick = { onCancel(booking.bookingNumber) }) {
                        Text("Cancel", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}
