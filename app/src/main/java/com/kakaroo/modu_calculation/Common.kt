package com.kakaroo.modu_calculation

object Common {
    val	CITY_SEOUL	    :Int	=	0
    val	CITY_NEWYORK	:Int	=	1
    val	CITY_PARIS	    :Int	=	2
    val	CITY_TOKYO	    :Int	=	3
    val	CITY_LONDON	    :Int	=	4
    val	CITY_ROME	    :Int	=	5
    val	CITY_BANGKOK	:Int	=	6
    val	CITY_BEIJING	:Int	=	7
    val	CITY_QUEBEC	    :Int	=	8
    val	CITY_DUBAI	    :Int	=	9
    val	CITY_CAIRO	    :Int	=	10
    val	CITY_SYDNEY	    :Int	=	11
    val	CITY_TAIPEI	    :Int	=	12
    val	CITY_SAOPAULO	:Int	=	13
    val	CITY_PRAGUE	    :Int	=	14
    val	CITY_BERLIN	    :Int	=	15
    val	CITY_MOSCOW	    :Int	=	16
    val	CITY_GENEVA	    :Int	=	17
    val	CITY_PHUKET	    :Int	=	18
    val	CITY_HAWAII	    :Int	=	19
    val	CITY_DOKDO	    :Int	=	20
    val	CITY_BALI	    :Int	=	21
    val	CITY_TAHITI	    :Int	=	22
    val	CITY_MAX	    :Int	=	23

    val	CITY_NAME_SEOUL     :String	= "seoul"
    val	CITY_NAME_NEWYORK	:String	= "newyork"
    val	CITY_NAME_PARIS     :String	= "paris"
    val	CITY_NAME_TOKYO     :String	= "tokyo"
    val	CITY_NAME_LONDON	:String	= "london"
    val	CITY_NAME_ROME      :String	= "rome"
    val	CITY_NAME_BANGKOK	:String	= "bangkok"
    val	CITY_NAME_BEIJING	:String	= "beijing"
    val	CITY_NAME_QUEBEC	:String	= "quebec"
    val	CITY_NAME_DUBAI     :String	= "dubai"
    val	CITY_NAME_CAIRO     :String	= "cairo"
    val	CITY_NAME_SYDNEY	:String	= "sydney"
    val	CITY_NAME_TAIPEI	:String	= "taipei"
    val	CITY_NAME_SAOPAULO	:String	= "saopaulo"
    val	CITY_NAME_PRAGUE	:String	= "prague"
    val	CITY_NAME_BERLIN	:String	= "berlin"
    val	CITY_NAME_MOSCOW	:String	= "moscow"
    val	CITY_NAME_GENEVA	:String	= "geneva"
    val	CITY_NAME_PHUKET	:String	= "phuket"
    val	CITY_NAME_HAWAII	:String	= "hawaii"
    val	CITY_NAME_DOKDO	    :String	= "dokdo"
    val	CITY_NAME_BALI	    :String	= "bali"
    val	CITY_NAME_TAHITI	:String	= "tahiti"


    val CITY_NAME_LIST = arrayOf<String>(CITY_NAME_SEOUL,
        CITY_NAME_NEWYORK,
        CITY_NAME_PARIS,
        CITY_NAME_TOKYO,
        CITY_NAME_LONDON,
        CITY_NAME_ROME,
        CITY_NAME_BANGKOK,
        CITY_NAME_BEIJING,
        CITY_NAME_QUEBEC,
        CITY_NAME_DUBAI,
        CITY_NAME_CAIRO,
        CITY_NAME_SYDNEY,
        CITY_NAME_TAIPEI,
        CITY_NAME_SAOPAULO,
        CITY_NAME_PRAGUE,
        CITY_NAME_BERLIN,
        CITY_NAME_MOSCOW,
        CITY_NAME_GENEVA,
        CITY_NAME_PHUKET,
        CITY_NAME_HAWAII,
        CITY_NAME_DOKDO,
        CITY_NAME_BALI,
        CITY_NAME_TAHITI)

    val	TYPE_LAND	:Int	=	0
    val	TYPE_VILLA	:Int	=	1
    val	TYPE_BUILDING	:Int	=	2
    val	TYPE_HOTEL	:Int	=	3
    val	TYPE_LANDMARK	:Int	=	4
    val	TYPE_MAX	:Int	=	5

    val	COST_CONST	:Int	=	0
    val	COST_TOLL	:Int	=	1
    val	COST_ACQUIRE	:Int	=	2
    val	COST_SELL	:Int	=	3
    val	COST_MAX	:Int	=	4


    val TYPE_NAME_LAND   :String = "땅"
    val TYPE_NAME_VILLA   :String = "빌라"
    val TYPE_NAME_BUILDING   :String = "빌딩"
    val TYPE_NAME_HOTEL   :String = "호텔"
    val TYPE_NAME_LANDMARK   :String = "랜드마크"
    val TYPE_NAME_LIST = arrayOf<String>(TYPE_NAME_LAND, TYPE_NAME_VILLA, TYPE_NAME_BUILDING, TYPE_NAME_HOTEL, TYPE_NAME_LANDMARK)

    val TYPE_NAME_PARASOL   :String = "파라솔"
    val TYPE_NAME_BUNGALOW   :String = "방갈로"

    val INTENT_VIEW_ACTIVITY : Int = 100
    val INTENT_EDIT_ACTIVITY : Int = 101

    val INTENT_SUB_ACTIVITY_PARAM_NAME : String = "CITY_INDEX"

    val NAME_MONEY   :String = "만원"

    val CITY_MODE_VIEW : Int = 0
    val CITY_MODE_EDIT : Int = 1

}