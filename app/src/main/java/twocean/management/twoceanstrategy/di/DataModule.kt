package twocean.management.twoceanstrategy.di

import twocean.management.twoceanstrategy.data.repository.BookingRepository
import twocean.management.twoceanstrategy.data.repository.HRPIKOnboardingRepo
import twocean.management.twoceanstrategy.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        HRPIKOnboardingRepo(
            hrpikOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}