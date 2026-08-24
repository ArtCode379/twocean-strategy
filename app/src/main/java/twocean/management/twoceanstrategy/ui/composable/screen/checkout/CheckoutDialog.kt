package twocean.management.twoceanstrategy.ui.composable.screen.checkout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import twocean.management.twoceanstrategy.data.entity.BookingEntity

@Composable
fun CheckoutDialog(booking: BookingEntity, selectedDate: String, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onConfirm,
        title = { Text("Consultation confirmed") },
        text = {
            Text(
                "Booking #${booking.bookingNumber}\n\nPreferred date: $selectedDate\n\nYour consultant will be waiting in the online conference or at the office at the confirmed time.",
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("View bookings")
            }
        },
    )
}
