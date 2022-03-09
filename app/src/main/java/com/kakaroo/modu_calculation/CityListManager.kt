package com.kakaroo.modu_calculation

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.StringReader

object CityListManager {

    val ITEM_SEPERATOR : String = ","

    public fun parseCityList(mCityList: ArrayList<CityData>?, cityIndex: Int, content: String) {
        if(content == null) {
            Log.e("kakaroo", "CityListManager::content is null")
            return
        }

        val br : BufferedReader = BufferedReader(StringReader(content))

        val buildingList = ArrayList<CityData.BuildingData>()
        var listIndex : Int = 0

        while(true) {

            var line:String? = null
            line = br?.readLine()

            if(line == null) {
                break;
            }
            else {
                line = line?.trim()
            }

            var splitStr = line?.split(ITEM_SEPERATOR)
            var itemIndex : Int = 0
            var itemData = ArrayList<Int>()
            for( s : String? in splitStr) {
                if( s != null) {
                    val s = s?.trim()
                    itemData.add(s.toInt())
                    itemIndex++
                }
            }
            if(itemIndex > Common.COST_MAX) {
                Log.e("kakaroo", "parseCityList::index is overflow")
            }

            buildingList?.add(listIndex,
                CityData.BuildingData(Common.TYPE_NAME_LIST[listIndex],
                itemData.get(Common.COST_CONST)
                    , itemData.get(Common.COST_TOLL)
                    , itemData.get(Common.COST_ACQUIRE)
                    , itemData.get(Common.COST_SELL)) )

            listIndex++
        } //end of while

        //Database의 PrimaryKey는 1부터
        mCityList?.add(CityData(cityIndex+1, Common.CITY_NAME_LIST[cityIndex], Common.CITY_NAME_LIST[cityIndex], buildingList))

            try {
            br.close()
        } catch (e : IOException) {
            Log.e("kakaroo", "IOException occured")
        }
    }
}