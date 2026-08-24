package twocean.management.twoceanstrategy.data.repository

import twocean.management.twoceanstrategy.data.datastore.HRPIKOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HRPIKOnboardingRepo(
    private val hrpikOnboardingStoreManager: HRPIKOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return hrpikOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            hrpikOnboardingStoreManager.setOnboardedState(state)
        }
    }
}