package com.memo.base.manager.data

import com.memo.base.entity.model.User
import com.tencent.mmkv.MMKV

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-29 10:51
 */
class DataManager {

    private val mSaver by lazy { MMKV.defaultMMKV() }

    private object Holder {
        val instance = DataManager()
    }

    companion object {
        private const val USER = "USER"

        fun get() = Holder.instance
    }

    fun putUsers(user: User) {
        mSaver.encode(USER, user)
    }

    fun getUsers() = mSaver.decodeParcelable(USER, User::class.java)

}