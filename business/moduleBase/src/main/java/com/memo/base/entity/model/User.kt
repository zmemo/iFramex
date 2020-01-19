package com.memo.base.entity.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-29 11:06
 */
@Parcelize
data class User(var name: String, var age: Int) : Parcelable {

    override fun toString(): String {
        return "name='$name', age=$age\n"
    }
}