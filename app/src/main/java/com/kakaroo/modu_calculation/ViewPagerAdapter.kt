package com.kakaroo.modu_calculation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.layout_citylist.view.*

class ViewPagerAdapter(private val list: ArrayList<CityData>): PagerAdapter() {
    private var mContext: Context? = null

    private var mView: View? = null

    fun ViewPagerAdapter(context: Context) {
        mContext = context;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.layout_citylist, container, false)
        mView = view

        view.tvItemTitle.setText(list[position].getStringId(container.context))
        view.ivItem.setImageResource(list[position].getImageId(container.context))

        view.tv_cost_land1.setText((list[position].content[Common.TYPE_LAND].const_cost).toString())
        view.tv_cost_land2.setText((list[position].content[Common.TYPE_LAND].toll_cost).toString())
        view.tv_cost_land3.setText((list[position].content[Common.TYPE_LAND].acquire_cost).toString())
        view.tv_cost_land4.setText((list[position].content[Common.TYPE_LAND].sell_cost).toString())

        view.tv_cost_villa1.setText((list[position].content[Common.TYPE_VILLA].const_cost).toString())
        view.tv_cost_villa2.setText((list[position].content[Common.TYPE_VILLA].toll_cost).toString())
        view.tv_cost_villa3.setText((list[position].content[Common.TYPE_VILLA].acquire_cost).toString())
        view.tv_cost_villa4.setText((list[position].content[Common.TYPE_VILLA].sell_cost).toString())

        view.tv_cost_building1.setText((list[position].content[Common.TYPE_BUILDING].const_cost).toString())
        view.tv_cost_building2.setText((list[position].content[Common.TYPE_BUILDING].toll_cost).toString())
        view.tv_cost_building3.setText((list[position].content[Common.TYPE_BUILDING].acquire_cost).toString())
        view.tv_cost_building4.setText((list[position].content[Common.TYPE_BUILDING].sell_cost).toString())

        if(position <= Common.CITY_GENEVA) {
            view.tv_cost_hotel1.setText((list[position].content[Common.TYPE_HOTEL].const_cost).toString())
            view.tv_cost_hotel2.setText((list[position].content[Common.TYPE_HOTEL].toll_cost).toString())
            view.tv_cost_hotel3.setText((list[position].content[Common.TYPE_HOTEL].acquire_cost).toString())
            view.tv_cost_hotel4.setText((list[position].content[Common.TYPE_HOTEL].sell_cost).toString())

            view.tv_cost_landmark1.setText((list[position].content[Common.TYPE_LANDMARK].const_cost).toString())
            view.tv_cost_landmark2.setText((list[position].content[Common.TYPE_LANDMARK].toll_cost).toString())
            if (list[position].content[Common.TYPE_LANDMARK].acquire_cost == 0) {
                view.tv_cost_landmark3.setText(R.string.no_acquire)
            } else {
                view.tv_cost_landmark3.setText((list[position].content[Common.TYPE_LANDMARK].acquire_cost).toString())
            }
            view.tv_cost_landmark4.setText((list[position].content[Common.TYPE_LANDMARK].sell_cost).toString())
        }
        else {  //휴양지
            view.tv_det_villa.setText(R.string.parasol)
            view.tv_det_building.setText(R.string.bungalow)
            view.tv_det_hotel.visibility = View.INVISIBLE
            view.tv_det_landmark.visibility = View.INVISIBLE

            //인수비용 View.GONE
            view.tv_det_acquire.visibility = View.GONE
            view.tv_cost_land3.visibility = View.GONE
            view.tv_cost_villa3.visibility = View.GONE
            view.tv_cost_building3.visibility = View.GONE
            view.tv_cost_hotel3.visibility = View.GONE
            view.tv_cost_landmark3.visibility = View.GONE

            view.tv_cost_villa1.visibility = View.INVISIBLE
            view.tv_cost_building1.visibility = View.INVISIBLE
            view.tv_cost_hotel1.visibility = View.INVISIBLE
            view.tv_cost_hotel2.visibility = View.INVISIBLE
            view.tv_cost_hotel4.visibility = View.INVISIBLE
            view.tv_cost_landmark1.visibility = View.INVISIBLE
            view.tv_cost_landmark2.visibility = View.INVISIBLE
            view.tv_cost_landmark4.visibility = View.INVISIBLE
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return list.size
    }

    fun getView(): View? {
        return mView
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE//super.getItemPosition(`object`)
    }


}