package com.kakaroo.modu_calculation

import android.content.Context

import androidx.room.*

@Entity(tableName = "tb_cityData")
data class CityData(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "t_id") val id: Int,
                    @ColumnInfo(name = "cityName") var cityname: String,
                    @ColumnInfo(name = "cityName2")var image: String,
                    @ColumnInfo(name = "buildingList") var content: ArrayList<BuildingData>)
{
    fun getStringId(context: Context): Int
    {
        return context.resources.getIdentifier(cityname, "string", context.packageName)
    }

    fun getImageId(context: Context): Int
    {
        return context.resources.getIdentifier(image, "drawable", context.packageName)
    }

    //땅, 빌라, 빌딩, 호텔, 랜드마크
    class BuildingData (var buildname: String, var const_cost: Int, var toll_cost: Int, var acquire_cost: Int, var sell_cost: Int)
    {

    }
}