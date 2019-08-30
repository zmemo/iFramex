package com.memo.tool.dialog.entity

import androidx.annotation.Keep
import com.contrarywind.interfaces.IPickerViewData

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-05-05 15:17
 */
@Keep
data class Province(
    val city: ArrayList<City> = arrayListOf(),
    val name: String = "" // 香港
) : IPickerViewData {
    override fun getPickerViewText() = name
}

@Keep
data class City(
    val area: ArrayList<String> = arrayListOf(),
    val name: String = "" // 香港
) : IPickerViewData {
    override fun getPickerViewText() = name
}

@Keep
data class Area(
    val provinces: List<Province> = arrayListOf(),
    val cities: List<List<String>> = arrayListOf(),
    val areas: List<List<List<String>>> = arrayListOf()
)