package com.kakaroo.modu_calculation

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.kakaroo.modu_calculation.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    object mCityList : ArrayList<CityData>() {
        //private var mCityList : ArrayList<CityData>? = null
    }

    //전역변수로 binding 객체선언
    private var mBinding: ActivityMainBinding? = null

    private var currCityIndex: Int = Common.CITY_SEOUL

    var db: CityDataDatabase? = null

    //private var mCityList : ArrayList<CityData>? = null

    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configure_UI()
        configureDB(false)
/*
        db = CityDataDatabase.getInstance(this)
        db!!.CityDataDao().getAll()
            .observe(this, object : Observerist<CityData?>() {
                fun onChanged(cityList: List<CityData?>) {
                    Toast.makeText(this, "DB Changed, ${cityList.toString()}", Toast.LENGTH_LONG).show()
                }
            })
       */
    } //end of onCreate function

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_change_cost -> callActivity(Common.INTENT_EDIT_ACTIVITY, currCityIndex)
            R.id.menu_restore_cost -> showDialogBoxforResetDB()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDialogBoxforResetDB() {
        var builder = AlertDialog.Builder(this, R.style.MyDialogTheme)
        builder.setTitle(R.string.app_name)
        builder.setMessage(R.string.do_you_want_to_reset_cityList)
        builder.setIcon(R.mipmap.ic_launcher_round)

        // 버튼 클릭시에 무슨 작업을 할 것인가!
        var listener = object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                when (p1) {
                    DialogInterface.BUTTON_POSITIVE -> configureDB(true)
                    DialogInterface.BUTTON_NEGATIVE -> null
                }
            }
        }
        builder.setPositiveButton("예", listener)
        builder.setNegativeButton("아니오", listener)

        builder.show()
    }

    fun initialCheckbox() {
        binding.ivVilla.visibility = View.INVISIBLE
        binding.ivBuilding.visibility = View.INVISIBLE
        binding.ivHotel.visibility = View.INVISIBLE
        binding.ivLandmark.visibility = View.INVISIBLE

        binding.checkBoxX2.isChecked = false
        binding.checkBoxX3.isChecked = false
        binding.checkBoxX4.isChecked = false
        binding.checkBoxX5.isChecked = false

        binding.checkBoxFlag.isChecked = false
        binding.checkBoxParasol.isChecked = false
        binding.checkBoxBungalow.isChecked = false

        binding.checkBoxLand.isChecked = false
        binding.checkBoxVilla.isChecked = false
        binding.checkBoxBuilding.isChecked = false
        binding.checkBoxHotel.isChecked = false
        binding.checkBoxLandmark.isChecked = false
        binding.checkBoxFestivalDouble.isChecked = false
        binding.checkBoxColorDouble.isChecked = false
    }

    fun setEnableDisableCheckBox() {
        var chkBoxCityEnable : Boolean = false
        var chkBoxVacationEnable : Boolean = false

        if(currCityIndex <= Common.CITY_GENEVA) {
            chkBoxCityEnable = true
            chkBoxVacationEnable = false
        }
        else {
            chkBoxCityEnable = false
            chkBoxVacationEnable = true
        }

        binding.checkBoxFlag.isEnabled = chkBoxVacationEnable
        binding.checkBoxParasol.isEnabled = chkBoxVacationEnable
        binding.checkBoxBungalow.isEnabled = chkBoxVacationEnable

        binding.checkBoxX2.isEnabled = chkBoxCityEnable
        binding.checkBoxX3.isEnabled = chkBoxCityEnable
        binding.checkBoxX4.isEnabled = chkBoxCityEnable
        binding.checkBoxX5.isEnabled = chkBoxCityEnable

        binding.checkBoxLand.isEnabled = chkBoxCityEnable
        binding.checkBoxVilla.isEnabled = chkBoxCityEnable
        binding.checkBoxBuilding.isEnabled = chkBoxCityEnable
        binding.checkBoxHotel.isEnabled = chkBoxCityEnable
        binding.checkBoxLandmark.isEnabled = chkBoxCityEnable
        binding.checkBoxFestivalDouble.isEnabled = chkBoxCityEnable
        binding.checkBoxColorDouble.isEnabled = chkBoxCityEnable
    }


    inner class ButtonListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val iv_main_img = findViewById<ImageView>(R.id.iv_city_picture);
            binding.btnGroup1.setBackgroundColor(R.color.purple_700)    //전체 도시 버튼 색상 초기화
            when (v?.id) {
                R.id.btn_seoul -> {
                    initialCheckbox()
                    currCityIndex = Common.CITY_SEOUL
                    binding.ivCityPicture.setImageResource(R.drawable.seoul)
                }
                R.id.btn_newyork -> {
                    currCityIndex = Common.CITY_NEWYORK
                    binding.ivCityPicture.setImageResource(R.drawable.newyork)
                }
                R.id.btn_bangkok -> {
                    currCityIndex = Common.CITY_BANGKOK
                    binding.ivCityPicture.setImageResource(R.drawable.bangkok)
                }
                R.id.btn_beijing -> {
                    currCityIndex = Common.CITY_BEIJING
                    binding.ivCityPicture.setImageResource(R.drawable.beijing)
                }
                R.id.btn_berlin -> {
                    currCityIndex = Common.CITY_BERLIN
                    binding.ivCityPicture.setImageResource(R.drawable.berlin)
                }
                R.id.btn_cairo -> {
                    currCityIndex = Common.CITY_CAIRO
                    binding.ivCityPicture.setImageResource(R.drawable.cairo)
                }
                R.id.btn_paris -> {
                    currCityIndex = Common.CITY_PARIS
                    binding.ivCityPicture.setImageResource(R.drawable.paris)
                }
                R.id.btn_dubai -> {
                    currCityIndex = Common.CITY_DUBAI
                    binding.ivCityPicture.setImageResource(R.drawable.dubai)
                }
                R.id.btn_geneva -> {
                    currCityIndex = Common.CITY_GENEVA
                    binding.ivCityPicture.setImageResource(R.drawable.geneva)
                }
                R.id.btn_london -> {
                    currCityIndex = Common.CITY_LONDON
                    binding.ivCityPicture.setImageResource(R.drawable.london)
                }
                R.id.btn_moscow -> {
                    currCityIndex = Common.CITY_MOSCOW
                    binding.ivCityPicture.setImageResource(R.drawable.moscow)
                }
                R.id.btn_prague -> {
                    currCityIndex = Common.CITY_PRAGUE
                    binding.ivCityPicture.setImageResource(R.drawable.prague)
                }
                R.id.btn_quebec -> {
                    currCityIndex = Common.CITY_QUEBEC
                    binding.ivCityPicture.setImageResource(R.drawable.quebec)
                }
                R.id.btn_rome -> {
                    currCityIndex = Common.CITY_ROME
                    binding.ivCityPicture.setImageResource(R.drawable.rome)
                }
                R.id.btn_saopaulo -> {
                    currCityIndex = Common.CITY_SAOPAULO
                    binding.ivCityPicture.setImageResource(R.drawable.saopaulo)
                }
                R.id.btn_sydney -> {
                    currCityIndex = Common.CITY_SYDNEY
                    binding.ivCityPicture.setImageResource(R.drawable.sydney)
                }
                R.id.btn_taipei -> {
                    currCityIndex = Common.CITY_TAIPEI
                    binding.ivCityPicture.setImageResource(R.drawable.taipei)
                }
                R.id.btn_tokyo -> {
                    currCityIndex = Common.CITY_TOKYO
                    binding.ivCityPicture.setImageResource(R.drawable.tokyo)
                }

                R.id.btn_phuket -> {
                    currCityIndex = Common.CITY_PHUKET
                    binding.ivCityPicture.setImageResource(R.drawable.phuket)
                }
                R.id.btn_hawaii -> {
                    currCityIndex = Common.CITY_HAWAII
                    binding.ivCityPicture.setImageResource(R.drawable.hawaii)
                }
                R.id.btn_dokdo -> {
                    currCityIndex = Common.CITY_DOKDO
                    binding.ivCityPicture.setImageResource(R.drawable.dokdo)
                }
                R.id.btn_bali -> {
                    currCityIndex = Common.CITY_BALI
                    binding.ivCityPicture.setImageResource(R.drawable.bali)
                }
                R.id.btn_tahiti -> {
                    currCityIndex = Common.CITY_TAHITI
                    binding.ivCityPicture.setImageResource(R.drawable.tahiti)
                }

                R.id.btn_construction_cost -> calculateCost(Common.COST_CONST, false)
                R.id.btn_toll_fee -> calculateCost(Common.COST_TOLL, false)
                R.id.btn_acquisition_cost -> calculateCost(Common.COST_ACQUIRE, false)
                R.id.btn_sell_cost -> calculateCost(Common.COST_SELL, false)
                R.id.btn_toll_acquisition_cost -> calculateCost(Common.COST_TOLL, true)

                else -> null
            }

            if(v?.id == R.id.btn_construction_cost || v?.id == R.id.btn_toll_fee || v?.id == R.id.btn_acquisition_cost
                || v?.id == R.id.btn_sell_cost || v?.id == R.id.btn_toll_acquisition_cost) {
                //Nothing
            }
            else if(v?.id == R.id.btn_phuket || v?.id == R.id.btn_hawaii || v?.id == R.id.btn_dokdo
                || v?.id == R.id.btn_bali || v?.id == R.id.btn_tahiti) {
                //binding.checkBoxFlag.isChecked = true

                setEnableDisableCheckBox()
            }
            else {  //Selected City
                binding.checkBoxLand.isChecked = true

                setEnableDisableCheckBox()
            }
        }
    }

    inner class ButtonLongClickListener : View.OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {
            var cityIndex: Int = Common.CITY_SEOUL
            when (v?.id) {

                R.id.btn_seoul -> cityIndex = Common.CITY_SEOUL
                R.id.btn_newyork -> cityIndex = Common.CITY_NEWYORK
                R.id.btn_paris -> cityIndex = Common.CITY_PARIS
                R.id.btn_tokyo -> cityIndex = Common.CITY_TOKYO
                R.id.btn_london -> cityIndex = Common.CITY_LONDON
                R.id.btn_rome -> cityIndex = Common.CITY_ROME
                R.id.btn_bangkok -> cityIndex = Common.CITY_BANGKOK
                R.id.btn_beijing -> cityIndex = Common.CITY_BEIJING
                R.id.btn_quebec -> cityIndex = Common.CITY_QUEBEC
                R.id.btn_dubai -> cityIndex = Common.CITY_DUBAI
                R.id.btn_cairo -> cityIndex = Common.CITY_CAIRO
                R.id.btn_sydney -> cityIndex = Common.CITY_SYDNEY
                R.id.btn_taipei -> cityIndex = Common.CITY_TAIPEI
                R.id.btn_saopaulo -> cityIndex = Common.CITY_SAOPAULO
                R.id.btn_prague -> cityIndex = Common.CITY_PRAGUE
                R.id.btn_berlin -> cityIndex = Common.CITY_BERLIN
                R.id.btn_moscow -> cityIndex = Common.CITY_MOSCOW
                R.id.btn_geneva -> cityIndex = Common.CITY_GENEVA
                R.id.btn_phuket -> cityIndex = Common.CITY_PHUKET
                R.id.btn_hawaii -> cityIndex = Common.CITY_HAWAII
                R.id.btn_dokdo -> cityIndex = Common.CITY_DOKDO
                R.id.btn_bali -> cityIndex = Common.CITY_BALI
                R.id.btn_tahiti -> cityIndex = Common.CITY_TAHITI

                else -> null
            }
            callActivity(Common.INTENT_VIEW_ACTIVITY, cityIndex)
            return true
        }
    }

    fun callActivity(activityType: Int, index: Int) {
        if(activityType == Common.INTENT_VIEW_ACTIVITY) {
            val intent = Intent(this, CityActivity::class.java)
            Log.d("Kakaroo", "Call index = $index")
            intent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, index)
            startActivityForResult(intent, activityType)
        } else if(activityType == Common.INTENT_EDIT_ACTIVITY) {
            val intent = Intent(this, EditCityActivity::class.java)
            Log.d("Kakaroo", "Call index = $index")
            intent.putExtra(Common.INTENT_SUB_ACTIVITY_PARAM_NAME, index)
            startActivityForResult(intent, activityType)
        } else {
            return
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Common.INTENT_VIEW_ACTIVITY -> {
                    val doc = data!!.getIntExtra("index", 0)
                }
                Common.INTENT_EDIT_ACTIVITY -> {
                    val doc = data!!.getIntExtra("index", 0)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume();
    }

    override fun onDestroy() { // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }

    fun configure_UI() {
        //image view
        binding.ivVilla.visibility = View.INVISIBLE
        binding.ivBuilding.visibility = View.INVISIBLE
        binding.ivHotel.visibility = View.INVISIBLE
        binding.ivLandmark.visibility = View.INVISIBLE

        // CheckBox
        binding.checkBoxLand.isChecked = false
        binding.checkBoxVilla.isChecked = false
        binding.checkBoxBuilding.isChecked = false
        binding.checkBoxHotel.isChecked = false
        binding.checkBoxLandmark.isChecked = false

        var chkBoxListener = CompoundButton.OnCheckedChangeListener { chkBoxView, isChecked ->
            if (isChecked) {
                when (chkBoxView.id) {
                    R.id.checkBox_villa -> binding.ivVilla.visibility = View.VISIBLE
                    R.id.checkBox_building -> binding.ivBuilding.visibility = View.VISIBLE
                    R.id.checkBox_hotel -> binding.ivHotel.visibility = View.VISIBLE
                    R.id.checkBox_landmark -> binding.ivLandmark.visibility = View.VISIBLE

                    //select only one item
                    R.id.checkBox_x2 -> {
                        binding.checkBoxX3.isChecked = false
                        binding.checkBoxX4.isChecked = false
                        binding.checkBoxX5.isChecked = false
                    }
                    R.id.checkBox_x3 -> {
                        binding.checkBoxX2.isChecked = false
                        binding.checkBoxX4.isChecked = false
                        binding.checkBoxX5.isChecked = false
                    }
                    R.id.checkBox_x4 -> {
                        binding.checkBoxX2.isChecked = false
                        binding.checkBoxX3.isChecked = false
                        binding.checkBoxX5.isChecked = false
                    }
                    R.id.checkBox_x5 -> {
                        binding.checkBoxX2.isChecked = false
                        binding.checkBoxX3.isChecked = false
                        binding.checkBoxX4.isChecked = false
                    }

                    //select only one item
                    R.id.checkBox_flag -> {
                        binding.checkBoxParasol.isChecked = false
                        binding.checkBoxBungalow.isChecked = false
                    }
                    R.id.checkBox_parasol -> {
                        binding.checkBoxFlag.isChecked = false
                        binding.checkBoxBungalow.isChecked = false
                    }
                    R.id.checkBox_bungalow -> {
                        binding.checkBoxFlag.isChecked = false
                        binding.checkBoxParasol.isChecked = false
                    }
                }
            } else {
                when (chkBoxView.id) {
                    R.id.checkBox_villa -> binding.ivVilla.visibility = View.INVISIBLE
                    R.id.checkBox_building -> binding.ivBuilding.visibility = View.INVISIBLE
                    R.id.checkBox_hotel -> binding.ivHotel.visibility = View.INVISIBLE
                    R.id.checkBox_landmark -> binding.ivLandmark.visibility = View.INVISIBLE
                }
            }
        }

        binding.checkBoxLand.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxVilla.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxBuilding.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxHotel.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxLandmark.setOnCheckedChangeListener(chkBoxListener)

        binding.checkBoxX2.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxX3.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxX4.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxX5.setOnCheckedChangeListener(chkBoxListener)

        binding.checkBoxFlag.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxParasol.setOnCheckedChangeListener(chkBoxListener)
        binding.checkBoxBungalow.setOnCheckedChangeListener(chkBoxListener)

        //Button
        binding.btnSeoul.setOnClickListener(ButtonListener())
        binding.btnNewyork.setOnClickListener(ButtonListener())
        binding.btnParis.setOnClickListener(ButtonListener())
        binding.btnBangkok.setOnClickListener(ButtonListener())
        binding.btnBeijing.setOnClickListener(ButtonListener())
        binding.btnCairo.setOnClickListener(ButtonListener())
        binding.btnDubai.setOnClickListener(ButtonListener())
        binding.btnBerlin.setOnClickListener(ButtonListener())
        binding.btnGeneva.setOnClickListener(ButtonListener())
        binding.btnLondon.setOnClickListener(ButtonListener())
        binding.btnMoscow.setOnClickListener(ButtonListener())
        binding.btnPrague.setOnClickListener(ButtonListener())
        binding.btnQuebec.setOnClickListener(ButtonListener())
        binding.btnRome.setOnClickListener(ButtonListener())
        binding.btnSaopaulo.setOnClickListener(ButtonListener())
        binding.btnSydney.setOnClickListener(ButtonListener())
        binding.btnTaipei.setOnClickListener(ButtonListener())
        binding.btnTokyo.setOnClickListener(ButtonListener())

        binding.btnPhuket.setOnClickListener(ButtonListener())
        binding.btnHawaii.setOnClickListener(ButtonListener())
        binding.btnDokdo.setOnClickListener(ButtonListener())
        binding.btnBali.setOnClickListener(ButtonListener())
        binding.btnTahiti.setOnClickListener(ButtonListener())

        binding.btnConstructionCost.setOnClickListener(ButtonListener())
        binding.btnTollFee.setOnClickListener(ButtonListener())
        binding.btnAcquisitionCost.setOnClickListener(ButtonListener())
        binding.btnSellCost.setOnClickListener(ButtonListener())
        binding.btnTollAcquisitionCost.setOnClickListener(ButtonListener())

        binding.btnSeoul.setOnLongClickListener(ButtonLongClickListener())
        binding.btnNewyork.setOnLongClickListener(ButtonLongClickListener())
        binding.btnParis.setOnLongClickListener(ButtonLongClickListener())
        binding.btnBangkok.setOnLongClickListener(ButtonLongClickListener())
        binding.btnBeijing.setOnLongClickListener(ButtonLongClickListener())
        binding.btnCairo.setOnLongClickListener(ButtonLongClickListener())
        binding.btnDubai.setOnLongClickListener(ButtonLongClickListener())
        binding.btnBerlin.setOnLongClickListener(ButtonLongClickListener())
        binding.btnGeneva.setOnLongClickListener(ButtonLongClickListener())
        binding.btnLondon.setOnLongClickListener(ButtonLongClickListener())
        binding.btnMoscow.setOnLongClickListener(ButtonLongClickListener())
        binding.btnPrague.setOnLongClickListener(ButtonLongClickListener())
        binding.btnQuebec.setOnLongClickListener(ButtonLongClickListener())
        binding.btnRome.setOnLongClickListener(ButtonLongClickListener())
        binding.btnSaopaulo.setOnLongClickListener(ButtonLongClickListener())
        binding.btnSydney.setOnLongClickListener(ButtonLongClickListener())
        binding.btnTaipei.setOnLongClickListener(ButtonLongClickListener())
        binding.btnTokyo.setOnLongClickListener(ButtonLongClickListener())

        binding.btnPhuket.setOnLongClickListener(ButtonLongClickListener())
        binding.btnHawaii.setOnLongClickListener(ButtonLongClickListener())
        binding.btnDokdo.setOnLongClickListener(ButtonLongClickListener())
        binding.btnBali.setOnLongClickListener(ButtonLongClickListener())
        binding.btnTahiti.setOnLongClickListener(ButtonLongClickListener())
    }

    fun configureCityListFromResource() {

        var stringArray = arrayListOf<String>()
        db = CityDataDatabase.getInstance(this)

        for (i: Int in 0..Common.CITY_MAX - 1) {
            val id: Int =
                this.resources.getIdentifier(Common.CITY_NAME_LIST[i], "array", this.packageName)
            stringArray = arrayListOf<String>(*resources.getStringArray(id))

            val sb = StringBuilder()
            for (s: String in stringArray) {
                sb.append(s)
                sb.append("\n")
            }
            CityListManager.parseCityList(mCityList, i, sb.toString())
            db?.CityDataDao()?.insert(mCityList[i]) //DB에 추가
        }
    }

    fun configureDB(delete: Boolean) {
        //초기화
        db = CityDataDatabase.getInstance(this)

        mCityList.clear()

        if (delete) {
            db!!.CityDataDao().deleteAll()
        }

        //이전에 저장한 내용 모두 불러와서 추가하기
        val cityList = db!!.CityDataDao().getAll()
        if (cityList.isNotEmpty()) {
            Log.d("kakaroo", "CityList from DB exist")
            mCityList.addAll(cityList)
        } else {
            Log.d("kakaroo", "CityList will be created from resource")
            configureCityListFromResource()
        }
    }

    fun isCurrIdxIncludedInCity() = if(currCityIndex <= Common.CITY_GENEVA) true else false

    fun calculateCost(index: Int, condition: Boolean = false) {
        var cost: Int = 0
        var costLand: Int = 0
        var costVilla: Int = 0
        var costBuilding: Int = 0
        var costHotel: Int = 0
        var costLandmark: Int = 0

        val isCheckedLand: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxLand.isChecked
                else binding.checkBoxFlag.isChecked
        val isCheckedVilla: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxVilla.isChecked
                else binding.checkBoxParasol.isChecked
        val isCheckedBuilding: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxBuilding.isChecked
                else binding.checkBoxBungalow.isChecked
        val isCheckedHotel: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxHotel.isChecked
                else false
        val isCheckedLandmark: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxLandmark.isChecked
                else false

        val isCheckedColorDouble: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxColorDouble.isChecked
                else false
        val isCheckedFestDouble: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxFestivalDouble.isChecked
                else false
        val isCheckedMultix2: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxX2.isChecked
                else false
        val isCheckedMultix3: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxX3.isChecked
                else false
        val isCheckedMultix4: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxX4.isChecked
                else false
        val isCheckedMultix5: Boolean = if(isCurrIdxIncludedInCity()) binding.checkBoxX5.isChecked
                else false

        when (index) {
            Common.COST_CONST -> {
                if(isCurrIdxIncludedInCity() == false) {
                    costLand = mCityList.get(currCityIndex).content[Common.TYPE_LAND].const_cost
                }
                else {
                    if (isCheckedLand) {
                        costLand = mCityList.get(currCityIndex).content[Common.TYPE_LAND].const_cost
                    }
                }
                if (isCheckedVilla) {
                    costVilla = mCityList.get(currCityIndex).content[Common.TYPE_VILLA].const_cost
                }
                if (isCheckedBuilding) {
                    costBuilding =
                        mCityList.get(currCityIndex).content[Common.TYPE_BUILDING].const_cost
                }
                if (isCheckedHotel) {
                    costHotel = mCityList.get(currCityIndex).content[Common.TYPE_HOTEL].const_cost
                }
                if (isCheckedLandmark) {
                    costLandmark =
                        mCityList.get(currCityIndex).content[Common.TYPE_LANDMARK].const_cost
                }

                binding.tvCost.setText(R.string.construction_cost)
            }
            Common.COST_TOLL -> {
                if (isCheckedLand) {
                    costLand = mCityList.get(currCityIndex).content[Common.TYPE_LAND].toll_cost
                }
                if (isCheckedVilla) {
                    costVilla = mCityList.get(currCityIndex).content[Common.TYPE_VILLA].toll_cost
                }
                if (isCheckedBuilding) {
                    costBuilding =
                        mCityList.get(currCityIndex).content[Common.TYPE_BUILDING].toll_cost
                }
                if (isCheckedHotel) {
                    costHotel = mCityList.get(currCityIndex).content[Common.TYPE_HOTEL].toll_cost
                }
                if (isCheckedLandmark) {
                    costLandmark =
                        mCityList.get(currCityIndex).content[Common.TYPE_LANDMARK].toll_cost
                }

                var multipleVal: Int = 1
                if (isCheckedColorDouble) {
                    multipleVal *= 2
                }
                if (isCheckedFestDouble) {
                    multipleVal *= 2
                }

                if (isCheckedMultix2) {
                    multipleVal *= 2
                }
                else if (isCheckedMultix3) {
                    multipleVal *= 3
                }
                else if (isCheckedMultix4) {
                    multipleVal *= 4
                }
                else if (isCheckedMultix5) {
                    multipleVal *= 5
                }
                costLand *= multipleVal
                costVilla *= multipleVal
                costBuilding *= multipleVal
                costHotel *= multipleVal
                costLandmark *= multipleVal

                if (condition == true)   //통행료 + 인수비용
                {
                    if (isCheckedLand) {
                        costLand += mCityList.get(currCityIndex).content[Common.TYPE_LAND].acquire_cost
                    }
                    if (isCheckedVilla) {
                        costVilla += mCityList.get(currCityIndex).content[Common.TYPE_VILLA].acquire_cost
                    }
                    if (isCheckedBuilding) {
                        costBuilding += mCityList.get(currCityIndex).content[Common.TYPE_BUILDING].acquire_cost
                    }
                    if (isCheckedHotel) {
                        costHotel += mCityList.get(currCityIndex).content[Common.TYPE_HOTEL].acquire_cost
                    }
                    if (isCheckedLandmark) {
                        costLandmark += mCityList.get(currCityIndex).content[Common.TYPE_LANDMARK].acquire_cost
                    }
                    binding.tvCost.setText(R.string.toll_acquisition_cost)
                }
                else {
                    binding.tvCost.setText(R.string.toll_fee)
                }
            }
            Common.COST_ACQUIRE -> {
                if (isCheckedLand) {
                    costLand += mCityList.get(currCityIndex).content[Common.TYPE_LAND].acquire_cost
                }

                if (isCheckedVilla) {
                    costVilla = mCityList.get(currCityIndex).content[Common.TYPE_VILLA].acquire_cost
                }
                if (isCheckedBuilding) {
                    costBuilding =
                        mCityList.get(currCityIndex).content[Common.TYPE_BUILDING].acquire_cost
                }
                if (isCheckedHotel) {
                    costHotel = mCityList.get(currCityIndex).content[Common.TYPE_HOTEL].acquire_cost
                }
                if (isCheckedLandmark) {
                    costLandmark =
                        mCityList.get(currCityIndex).content[Common.TYPE_LANDMARK].acquire_cost
                }
                binding.tvCost.setText(R.string.acquisition_cost)
            }
            Common.COST_SELL -> {
                if (isCheckedLand) {
                    costLand += mCityList.get(currCityIndex).content[Common.TYPE_LAND].sell_cost
                }
                if (isCheckedVilla) {
                    costVilla = mCityList.get(currCityIndex).content[Common.TYPE_VILLA].sell_cost
                }
                if (isCheckedBuilding) {
                    costBuilding =
                        mCityList.get(currCityIndex).content[Common.TYPE_BUILDING].sell_cost
                }
                if (isCheckedHotel) {
                    costHotel = mCityList.get(currCityIndex).content[Common.TYPE_HOTEL].sell_cost
                }
                if (isCheckedLandmark) {
                    costLandmark =
                        mCityList.get(currCityIndex).content[Common.TYPE_LANDMARK].sell_cost
                }
                binding.tvCost.setText(R.string.sale_cost)
            }
            else -> Log.d("kakaroo", "calculateCost:: index = $index")
        } //end of when


        cost = costLand + costVilla + costBuilding + costHotel + costLandmark
        binding.tvCostResult.text = cost.toString() + Common.NAME_MONEY

        var sb = StringBuilder()
        if (costLand > 0) {
            sb.append(Common.TYPE_NAME_LAND + " : " + costLand + Common.NAME_MONEY + "\n")
        }
        if (costVilla > 0) {
            if(currCityIndex <= Common.CITY_GENEVA) {
                sb.append(Common.TYPE_NAME_VILLA + " : " + costVilla + Common.NAME_MONEY + "\n")
            }
            else {
                sb.append(Common.TYPE_NAME_PARASOL + " : " + costVilla + Common.NAME_MONEY + "\n")
            }
        }
        if (costBuilding > 0) {
            if(currCityIndex <= Common.CITY_GENEVA) {
                sb.append(Common.TYPE_NAME_BUILDING + " : " + costBuilding + Common.NAME_MONEY + "\n")
            }
            else {
                sb.append(Common.TYPE_NAME_BUNGALOW + " : " + costBuilding + Common.NAME_MONEY + "\n")
            }
        }
        if (costHotel > 0) {
            sb.append(Common.TYPE_NAME_HOTEL + " : " + costHotel + Common.NAME_MONEY + "\n")
        }
        if (costLandmark > 0) {
            sb.append(Common.TYPE_NAME_LANDMARK + " : " + costLandmark + Common.NAME_MONEY + "\n")
        }
        binding.tvDetailDesc.text = sb.toString()
    }
}
