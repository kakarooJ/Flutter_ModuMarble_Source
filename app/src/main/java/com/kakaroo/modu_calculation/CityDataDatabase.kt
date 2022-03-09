package com.kakaroo.modu_calculation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CityData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CityDataDatabase : RoomDatabase() {
    abstract fun CityDataDao(): CityDataDao

    companion object {
        private var instance: CityDataDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CityDataDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDataDatabase::class.java,
                    "database-cityData"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}