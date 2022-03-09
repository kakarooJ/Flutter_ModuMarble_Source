package com.kakaroo.modu_calculation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_city.*


class CityActivity : AppCompatActivity() {

    private var mCurrIndex : Int = 0
    private var mCurrMode : Int = Common.CITY_MODE_VIEW
    private var mVpAdapter : ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        if (intent.hasExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME)) {
            mCurrIndex = intent.getIntExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, 0)
            Log.d("kakaroo", "CityActivity index:$mCurrIndex")
        }
        else {
            Log.e("kakaroo", "CityActivity index does not exist")
        }

        mVpAdapter = ViewPagerAdapter(MainActivity.mCityList)//(cityList)
        mViewPager?.adapter = mVpAdapter

        mViewPager?.setCurrentItem(mCurrIndex, true)
        if(btn_edit == null) {
            Log.e("kakaroo", "btn_edit is null")
        } else {
            btn_edit.setOnClickListener(ButtonListener())
        }

        //Edit 버튼을 activity_city.xml에 추가하고 listener를 등록하자
        //아니면 옵션 메뉴로...
        //Activity를 다시 띄우던가 새로운 Activity를 호출하자
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //Log.d("Kakaroo", "$position th page scrolled")
            }
            override fun onPageSelected(position: Int) {
                Log.d("Kakaroo", "$position th page selected")
                mCurrIndex = position
            }
            override fun onPageScrollStateChanged(state: Int) {
                //Log.d("Kakaroo", "$state onPageScrollStateChanged")
            }
        })
    } //end of onCreate


    inner class ButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_edit -> {
                    Log.d("Kakaroo", "EDIT button pressed")
                    callActivity(mCurrIndex)
                }
                else -> null
            }
        }
    } //end of inner class

    fun callActivity(index: Int) {
        val intent = Intent(this, EditCityActivity::class.java)
        Log.d("Kakaroo", "callActivity::Call index = $index")
        intent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, index)
        startActivityForResult(intent, Common.INTENT_EDIT_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Common.INTENT_EDIT_ACTIVITY -> {
                    val index = data!!.getIntExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, 0)
                    mVpAdapter?.notifyDataSetChanged()  //TODO : resume 시 변하지 않음
                    //mViewPager
                }
            }
        }
    }

    override fun onResume() {
        super.onResume();
    }


    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, mCurrIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
/*
    companion object {
        val cityList = arrayListOf(
            CityData("Seoul", "seoul",
                arrayListOf(
                    CityData.BuildingData("땅", 22, 8, 44, 11),
                    CityData.BuildingData("빌라", 8, 16, 16, 4),
                    CityData.BuildingData("빌딩", 24, 34, 48, 12),
                    CityData.BuildingData("호텔", 40, 70, 80, 20),
                    CityData.BuildingData("랜드마크", 40, 60, 0, 20) )
                ),
            CityData("New york", "newyork",
                arrayListOf(
                    CityData.BuildingData("땅", 20, 7, 40, 10),
                    CityData.BuildingData("빌라", 8, 15, 16, 4),
                    CityData.BuildingData("빌딩", 24,32,48,12),
                    CityData.BuildingData("호텔", 40,68,80,20),
                    CityData.BuildingData("랜드마크", 40, 60, 0, 20) )
                ),
            CityData("London", "london",
                arrayListOf(
                    CityData.BuildingData("땅", 3, 3, 3, 0),
                    CityData.BuildingData("빌라", 3, 3, 3, 1),
                    CityData.BuildingData("빌딩", 3, 3, 3, 2),
                    CityData.BuildingData("호텔", 3, 3, 3, 3),
                    CityData.BuildingData("랜드마크", 3, 3, 3, 4) )
                ),
            CityData("Paris", "paris",
                arrayListOf(
                    CityData.BuildingData("땅", 4, 4, 4, 0),
                    CityData.BuildingData("빌라", 4, 4, 4, 1),
                    CityData.BuildingData("빌딩", 4, 4, 4, 2),
                    CityData.BuildingData("호텔", 4, 4, 4, 3),
                    CityData.BuildingData("랜드마크", 4, 4, 4, 4) )
                )
            )

    }*/

}