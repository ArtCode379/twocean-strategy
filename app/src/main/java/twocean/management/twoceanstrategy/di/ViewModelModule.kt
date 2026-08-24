package twocean.management.twoceanstrategy.di

import twocean.management.twoceanstrategy.ui.viewmodel.BookingViewModel
import twocean.management.twoceanstrategy.ui.viewmodel.CheckoutViewModel
import twocean.management.twoceanstrategy.ui.viewmodel.HRPIKOnboardingVM
import twocean.management.twoceanstrategy.ui.viewmodel.ServiceDetailsViewModel
import twocean.management.twoceanstrategy.ui.viewmodel.ServiceViewModel
import twocean.management.twoceanstrategy.ui.viewmodel.HRPIKSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        HRPIKSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        HRPIKOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}