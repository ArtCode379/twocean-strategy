package twocean.management.twoceanstrategy.di

import androidx.room.Room
import twocean.management.twoceanstrategy.data.database.HRPIKDatabase
import org.koin.dsl.module

private const val DB_NAME = "hrpik_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = HRPIKDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<HRPIKDatabase>().bookingDao()}

}