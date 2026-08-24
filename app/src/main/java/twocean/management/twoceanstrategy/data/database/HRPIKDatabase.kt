package twocean.management.twoceanstrategy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import twocean.management.twoceanstrategy.data.dao.BookingDao
import twocean.management.twoceanstrategy.data.database.converter.Converters
import twocean.management.twoceanstrategy.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HRPIKDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

