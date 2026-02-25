package com.anjegonz.bikeworkshop.garage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anjegonz.bikeworkshop.garage.data.converters.Converters

@Database(
    entities = [MotorcycleEntity::class],
    version = 1
)
@TypeConverters(
    Converters::class
)
abstract class MotorcycleDatabase: RoomDatabase() {
    abstract val motorcycleDao: MotorcycleDao

    companion object {
        const val DB_NAME = "motorcycles.db"
    }
}