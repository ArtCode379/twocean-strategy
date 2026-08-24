package twocean.management.twoceanstrategy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import twocean.management.twoceanstrategy.data.model.ServiceModel
import twocean.management.twoceanstrategy.data.repository.ServiceRepository
import twocean.management.twoceanstrategy.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    private val _servicesState =
        MutableStateFlow<DataUiState<List<ServiceModel>>>(DataUiState.Initial)
    val servicesState: StateFlow<DataUiState<List<ServiceModel>>>
        get() = _servicesState.asStateFlow()

    init {
        observeServices()
    }

    private fun observeServices() {
        viewModelScope.launch {
            serviceRepository.observeAll().collect { services ->
                _servicesState.update {
                    DataUiState.from(services)
                }
            }
        }
    }
}