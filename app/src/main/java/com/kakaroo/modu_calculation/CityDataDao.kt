package com.kakaroo.modu_calculation

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CityDataDao {
    @Query("SELECT * FROM tb_cityData")
    fun getAll(): List<CityData>

    @Insert(onConflict = REPLACE)
    fun insert(cityData: CityData)

    @Transaction
    open fun updateData(cityData: CityData) {
        delete(cityData)
        insert(cityData)
    }

    @Delete
    fun delete(cityData: CityData)

    @Query("DELETE FROM tb_cityData")
    fun deleteAll()

}
