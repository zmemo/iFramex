package com.memo.base.entity.model

import android.os.Parcel
import android.os.Parcelable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-29 11:06
 */
data class User(var name: String, var age: Int) : Parcelable {

    override fun toString(): String {
        return "name='$name', age=$age\n"
    }

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(age)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}