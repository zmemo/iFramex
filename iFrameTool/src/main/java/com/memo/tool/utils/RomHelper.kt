package com.memo.tool.utils

import android.os.Build
import android.text.TextUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * title: 厂家Rom判断
 * describe:
 *
 * @author zhou
 * @date 2019-07-26 15:48
 */
object RomHelper {

    val ROM_MIUI = "MIUI"
    val ROM_EMUI = "EMUI"
    val ROM_FLYME = "FLYME"
    val ROM_OPPO = "OPPO"
    val ROM_SMARTISAN = "SMARTISAN"
    val ROM_VIVO = "VIVO"
    val ROM_QIKU = "QIKU"
    val ROM_360 = "360"

    private val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
    private val KEY_VERSION_EMUI = "ro.build.version.emui"
    private val KEY_VERSION_OPPO = "ro.build.version.opporom"
    private val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
    private val KEY_VERSION_VIVO = "ro.vivo.os.version"

    private var sName: String? = null
    private var sVersion: String? = null

    /**
     * 华为
     */
    val isEmui: Boolean
        get() = check(ROM_EMUI)

    /**
     * 小米
     */
    val isMiui: Boolean
        get() = check(ROM_MIUI)

    /**
     * Vivo
     */
    val isVivo: Boolean
        get() = check(ROM_VIVO)

    /**
     * Oppo
     */
    val isOppo: Boolean
        get() = check(ROM_OPPO)

    /**
     * 魅族
     */
    val isFlyme: Boolean
        get() = check(ROM_FLYME)

    /**
     * 360
     */
    val is360: Boolean
        get() = check(ROM_QIKU) || check(ROM_360)

    /**
     * 锤子
     */
    val isSmartisan: Boolean
        get() = check(ROM_SMARTISAN)

    private val name: String?
        get() {
            if (sName == null) {
                check("")
            }
            return sName
        }

    private val version: String?
        get() {
            if (sVersion == null) {
                check("")
            }
            return sVersion
        }

    private fun check(rom: String): Boolean {
        if (sName != null) {
            return sName == rom
        }

        if (!TextUtils.isEmpty(getProp(KEY_VERSION_MIUI))) {
            sName = ROM_MIUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_EMUI))) {
            sName = ROM_EMUI
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_OPPO))) {
            sName = ROM_OPPO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_VIVO))) {
            sName = ROM_VIVO
        } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_SMARTISAN))) {
            sName = ROM_SMARTISAN
        } else {
            sVersion = Build.DISPLAY
            if (sVersion!!.toUpperCase().contains(ROM_FLYME)) {
                sName = ROM_FLYME
            } else {
                sVersion = Build.UNKNOWN
                sName = Build.MANUFACTURER.toUpperCase()
            }
        }
        return sName == rom
    }

    private fun getProp(name: String): String? {
        var line: String? = null
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $name")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            return null
        } finally {
            IOHelper.close(input)
        }
        return line
    }
}