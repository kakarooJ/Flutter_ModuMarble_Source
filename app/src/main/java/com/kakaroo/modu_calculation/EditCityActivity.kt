package com.kakaroo.modu_calculation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.layout_citylist.*

class EditCityActivity : AppCompatActivity() {
    private var currIndex : Int = 0
    private val mCityList = MainActivity.mCityList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_citylist)

        if (intent.hasExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME)) {
            currIndex = intent.getIntExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, 0)
            Log.d("kakaroo", "EditCityActivity index:$currIndex")
        }
        else {
            Log.e("kakaroo", "EditCityActivity index does not exist")
        }

        configureUI()

    }   //end of onCreate

    fun configureUI() {
        ivItem.visibility = View.GONE
        btn_save.visibility = View.VISIBLE

        tvItemTitle.setText(mCityList[currIndex].getStringId(this))
        ivItem.setImageResource(mCityList[currIndex].getImageId(this))

        tv_cost_land1.setText((mCityList[currIndex].content[Common.TYPE_LAND].const_cost).toString())
        tv_cost_land2.setText((mCityList[currIndex].content[Common.TYPE_LAND].toll_cost).toString())
        tv_cost_land3.setText((mCityList[currIndex].content[Common.TYPE_LAND].acquire_cost).toString())
        tv_cost_land4.setText((mCityList[currIndex].content[Common.TYPE_LAND].sell_cost).toString())

        tv_cost_villa1.setText((mCityList[currIndex].content[Common.TYPE_VILLA].const_cost).toString())
        tv_cost_villa2.setText((mCityList[currIndex].content[Common.TYPE_VILLA].toll_cost).toString())
        tv_cost_villa3.setText((mCityList[currIndex].content[Common.TYPE_VILLA].acquire_cost).toString())
        tv_cost_villa4.setText((mCityList[currIndex].content[Common.TYPE_VILLA].sell_cost).toString())

        tv_cost_building1.setText((mCityList[currIndex].content[Common.TYPE_BUILDING].const_cost).toString())
        tv_cost_building2.setText((mCityList[currIndex].content[Common.TYPE_BUILDING].toll_cost).toString())
        tv_cost_building3.setText((mCityList[currIndex].content[Common.TYPE_BUILDING].acquire_cost).toString())
        tv_cost_building4.setText((mCityList[currIndex].content[Common.TYPE_BUILDING].sell_cost).toString())

        if(currIndex <= Common.CITY_GENEVA) {
            tv_cost_hotel1.setText((mCityList[currIndex].content[Common.TYPE_HOTEL].const_cost).toString())
            tv_cost_hotel2.setText((mCityList[currIndex].content[Common.TYPE_HOTEL].toll_cost).toString())
            tv_cost_hotel3.setText((mCityList[currIndex].content[Common.TYPE_HOTEL].acquire_cost).toString())
            tv_cost_hotel4.setText((mCityList[currIndex].content[Common.TYPE_HOTEL].sell_cost).toString())

            tv_cost_landmark1.setText((mCityList[currIndex].content[Common.TYPE_LANDMARK].const_cost).toString())
            tv_cost_landmark2.setText((mCityList[currIndex].content[Common.TYPE_LANDMARK].toll_cost).toString())
            if (mCityList[currIndex].content[Common.TYPE_LANDMARK].acquire_cost == 0) {
                tv_cost_landmark3.setText(R.string.no_acquire)
            } else {
                tv_cost_landmark3.setText((mCityList[currIndex].content[Common.TYPE_LANDMARK].acquire_cost).toString())
            }
            tv_cost_landmark4.setText((mCityList[currIndex].content[Common.TYPE_LANDMARK].sell_cost).toString())
        }
        else {
            tv_det_villa.setText(R.string.parasol)
            tv_det_building.setText(R.string.bungalow)
            tv_det_hotel.visibility = View.INVISIBLE
            tv_det_landmark.visibility = View.INVISIBLE

            //인수비용 View.GONE
            tv_det_acquire.visibility = View.GONE
            tv_cost_land3.visibility = View.GONE
            tv_cost_villa3.visibility = View.GONE
            tv_cost_building3.visibility = View.GONE
            tv_cost_hotel3.visibility = View.GONE
            tv_cost_landmark3.visibility = View.GONE

            tv_cost_villa1.visibility = View.INVISIBLE
            tv_cost_building1.visibility = View.INVISIBLE
            tv_cost_hotel1.visibility = View.INVISIBLE
            tv_cost_hotel2.visibility = View.INVISIBLE
            tv_cost_hotel4.visibility = View.INVISIBLE
            tv_cost_landmark1.visibility = View.INVISIBLE
            tv_cost_landmark2.visibility = View.INVISIBLE
            tv_cost_landmark4.visibility = View.INVISIBLE
        }


        tv_cost_land1.isEnabled = true
        tv_cost_land2.isEnabled = true
        tv_cost_land3.isEnabled = true
        tv_cost_land4.isEnabled = true

        tv_cost_villa1.isEnabled = true
        tv_cost_villa2.isEnabled = true
        tv_cost_villa3.isEnabled = true
        tv_cost_villa4.isEnabled = true

        tv_cost_building1.isEnabled = true
        tv_cost_building2.isEnabled = true
        tv_cost_building3.isEnabled = true
        tv_cost_building4.isEnabled = true

        tv_cost_hotel1.isEnabled = true
        tv_cost_hotel2.isEnabled = true
        tv_cost_hotel3.isEnabled = true
        tv_cost_hotel4.isEnabled = true

        tv_cost_landmark1.isEnabled = true
        tv_cost_landmark2.isEnabled = true
        tv_cost_landmark3.isEnabled = true
        tv_cost_landmark4.isEnabled = true

        tv_cost_land1.isFocusable = true
        tv_cost_land2.isFocusable = true
        tv_cost_land3.isFocusable = true
        tv_cost_land4.isFocusable = true

        tv_cost_villa1.isFocusable = true
        tv_cost_villa2.isFocusable = true
        tv_cost_villa3.isFocusable = true
        tv_cost_villa4.isFocusable = true

        tv_cost_building1.isFocusable = true
        tv_cost_building2.isFocusable = true
        tv_cost_building3.isFocusable = true
        tv_cost_building4.isFocusable = true

        tv_cost_hotel1.isFocusable = true
        tv_cost_hotel2.isFocusable = true
        tv_cost_hotel3.isFocusable = true
        tv_cost_hotel4.isFocusable = true

        tv_cost_landmark1.isFocusable = true
        tv_cost_landmark2.isFocusable = true
        tv_cost_landmark3.isFocusable = true
        tv_cost_landmark4.isFocusable = true

        if(btn_save == null) {
            Log.e("kakaroo", "EditCityActivity::btn_save is null")
        } else {
            btn_save.setOnClickListener((ButtonListener()))
        }


    } //end of configureUI()

    inner class ButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_save -> {
                    Log.d("Kakaroo", "EditCityActivity::SAVE button pressed")
                    updateCityList()
                    update_DB()

                    val returnIntent = Intent()
                    returnIntent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, currIndex)
                    setResult(Activity.RESULT_OK, returnIntent)

                    finish()
                }
                else -> null
            }
        }
    } //end of inner class

    private fun updateCityList() {
        mCityList[currIndex].content[Common.TYPE_LAND].const_cost = tv_cost_land1.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_LAND].toll_cost = tv_cost_land2.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_LAND].acquire_cost = tv_cost_land3.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_LAND].sell_cost = tv_cost_land4.text.toString().toInt()

        mCityList[currIndex].content[Common.TYPE_VILLA].const_cost = tv_cost_villa1.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_VILLA].toll_cost = tv_cost_villa2.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_VILLA].acquire_cost = tv_cost_villa3.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_VILLA].sell_cost = tv_cost_villa4.text.toString().toInt()

        mCityList[currIndex].content[Common.TYPE_BUILDING].const_cost = tv_cost_building1.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_BUILDING].toll_cost = tv_cost_building2.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_BUILDING].acquire_cost = tv_cost_building3.text.toString().toInt()
        mCityList[currIndex].content[Common.TYPE_BUILDING].sell_cost = tv_cost_building4.text.toString().toInt()

        if(currIndex <= Common.CITY_GENEVA) {
            mCityList[currIndex].content[Common.TYPE_HOTEL].const_cost = tv_cost_hotel1.text.toString().toInt()
            mCityList[currIndex].content[Common.TYPE_HOTEL].toll_cost = tv_cost_hotel2.text.toString().toInt()
            mCityList[currIndex].content[Common.TYPE_HOTEL].acquire_cost = tv_cost_hotel3.text.toString().toInt()
            mCityList[currIndex].content[Common.TYPE_HOTEL].sell_cost = tv_cost_hotel4.text.toString().toInt()

            mCityList[currIndex].content[Common.TYPE_LANDMARK].const_cost = tv_cost_landmark1.text.toString().toInt()
            mCityList[currIndex].content[Common.TYPE_LANDMARK].toll_cost = tv_cost_landmark2.text.toString().toInt()

            if(tv_cost_landmark3.text.toString().toIntOrNull() != null) {
                mCityList[currIndex].content[Common.TYPE_LANDMARK].acquire_cost =
                    tv_cost_landmark3.text.toString().toInt()
            }
            mCityList[currIndex].content[Common.TYPE_LANDMARK].sell_cost = tv_cost_landmark4.text.toString().toInt()
        }
    }

    private fun update_DB() {
        var db : CityDataDatabase? = CityDataDatabase.getInstance(this)
        db?.CityDataDao()?.insert(mCityList[currIndex])

        //debug
        val cityList = db!!.CityDataDao().getAll()
        if (cityList.isNotEmpty()) {

        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, currIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}