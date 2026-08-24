package twocean.management.twoceanstrategy.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import twocean.management.twoceanstrategy.R
import twocean.management.twoceanstrategy.ui.composable.shared.HRPIKContentWrapper
import twocean.management.twoceanstrategy.ui.composable.shared.HRPIKEmptyView
import twocean.management.twoceanstrategy.ui.state.BookingUiState
import twocean.management.twoceanstrategy.ui.state.DataUiState
import twocean.management.twoceanstrategy.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel()
) {
    val bookingsState by viewModel.bookingsState.collectAsState()

    var canceledBookingNumber by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    BookingsContent(
        bookingsState = bookingsState,
        modifier = modifier,
        onCancelBookingButtonClick = { bookingNumber ->
            canceledBookingNumber = bookingNumber
            shouldShowDialog = true
        }
    )

    if (shouldShowDialog) {

    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    Column(modifier = modifier) {

        HRPIKContentWrapper(
            dataState = bookingsState,

            dataPopulated = {
                BookingsPopulated(
                    bookings = (bookingsState as DataUiState.Populated).data,
                    onCancelBookingButtonClick = onCancelBookingButtonClick,
                )
            },

            dataEmpty = {
                HRPIKEmptyView(
                    primaryText = stringResource(R.string.hrpik_bookings_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {

}