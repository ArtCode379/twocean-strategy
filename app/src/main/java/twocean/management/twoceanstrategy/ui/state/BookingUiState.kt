package twocean.management.twoceanstrategy.ui.state

data class BookingUiState(
    val serviceName: String,
    val bookingNumber: String,
    val customerFirstName: String,
    val customerLastName: String,
    val timestamp: String,
)