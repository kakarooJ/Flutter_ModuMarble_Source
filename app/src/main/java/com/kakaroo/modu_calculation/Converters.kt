package com.kakaroo.modu_calculation

import androidx.room.TypeConverter
import com.kakaroo.modu_calculation.CityData.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

//class Converters {
/*
    @TypeConverter
    fun listToJson(value: List<BuildingData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<BuildingData>::class.java).toList()
*/
class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<BuildingData> {
        val listType: Type = object : TypeToken<ArrayList<BuildingData?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<BuildingData?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
/*
    @TypeConverter
    fun stringAsStringList(strings: String?): List<String> {
        val list = mutableListOf<String>()
        strings
            ?.split(",")
            ?.forEach {
                list.add(it)
            }

        return list
    }

    @TypeConverter
    fun stringListAsString(strings: List<String>?): String {
        var result = ""
        strings?.forEach { element ->
            result += "$element,"
        }
        return result.removeSuffix(",")
    }

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromString(value: String?): List<BuildingData?>? {
            //BuildingData
            //val listType: Type =
             //   object : TypeToken<List<BuildingData?>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(list: List<BuildingData?>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }
    }
*/