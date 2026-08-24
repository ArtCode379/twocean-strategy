package twocean.management.twoceanstrategy.ui.composable.screen.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import twocean.management.twoceanstrategy.data.entity.BookingEntity
import twocean.management.twoceanstrategy.ui.state.DataUiState
import twocean.management.twoceanstrategy.ui.viewmodel.CheckoutViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun CheckoutScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToBookingsScreen: () -> Unit,
) {
    val bookingState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    var phone by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    if (bookingState is DataUiState.Populated) {
        CheckoutDialog((bookingState as DataUiState.Populated<BookingEntity>).data, selectedDate, onNavigateToBookingsScreen)
    }
    CheckoutContent(
        firstName = viewModel.customerFirstName,
        lastName = viewModel.customerLastName,
        email = viewModel.customerEmail,
        phone = phone,
        selectedDate = selectedDate,
        notes = notes,
        isEmailInvalid = emailInvalid,
        modifier = modifier,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPhoneChanged = { phone = it },
        onNotesChanged = { notes = it },
        onDateClick = { showDatePicker = true },
        onPlaceBooking = { viewModel.placeBooking(serviceId) },
    )
    if (showDatePicker) {
        val state = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        state.selectedDateMillis?.let {
                            selectedDate = Instant.ofEpochMilli(it)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                        }
                        showDatePicker = false
                    },
                ) {
                    Text("Select")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            },
        ) {
            DatePicker(state)
        }
    }
}

@Composable
private fun CheckoutContent(
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    selectedDate: String,
    notes: String,
    isEmailInvalid: Boolean,
    modifier: Modifier,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onNotesChanged: (String) -> Unit,
    onDateClick: () -> Unit,
    onPlaceBooking: () -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Book your consultation", style = MaterialTheme.typography.headlineMedium)
        Text("Tell us how to reach you and choose a preferred day. Your advisor will confirm the exact session time.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(firstName, onFirstNameChanged, "First name", Modifier.fillMaxWidth())
        CheckoutTextField(lastName, onLastNameChanged, "Last name", Modifier.fillMaxWidth())
        CheckoutTextField(email, onEmailChanged, "Email", Modifier.fillMaxWidth(), isError = isEmailInvalid, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        CheckoutTextField(phone, onPhoneChanged, "Phone", Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            readOnly = true,
            label = { Text("Preferred date") },
            trailingIcon = { Icon(Icons.Outlined.CalendarMonth, null) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDateClick),
        )
        OutlinedTextField(
            value = notes,
            onValueChange = onNotesChanged,
            label = { Text("What would you like to achieve?") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
        )
        Button(
            onClick = onPlaceBooking,
            enabled = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && selectedDate.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Confirm Booking")
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
    )
}
