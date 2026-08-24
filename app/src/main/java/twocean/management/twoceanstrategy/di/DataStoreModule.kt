package twocean.management.twoceanstrategy.di

import twocean.management.twoceanstrategy.data.datastore.HRPIKOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { HRPIKOnboardingPrefs(androidContext()) }
}