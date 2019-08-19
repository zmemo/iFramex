package com.memo.tool.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.Utils

/**
 * title:权限请求工具
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 17:25
 */
object PermissionHelper {

    private var useVideo: Boolean? = null
    private var useAudio: Boolean? = null

    private const val CAMERA = android.Manifest.permission.CAMERA
    private const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    private const val WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private const val READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE

    /**
     * 存储
     */
    @JvmStatic
    fun grantedStorage(context: Context): Boolean {
        if (PermissionUtils.isGranted(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)) {
            return true
        }
        request(context, PermissionConstants.STORAGE)
        return false
    }

    @JvmStatic
    fun grantedAudio(context: Context): Boolean {
        if (PermissionUtils.isGranted(RECORD_AUDIO)) {
            if (useAudio == null) {
                useAudio = MediaHelper.isAudioUsable
            }
            return useAudio!!
        }
        request(context, PermissionConstants.MICROPHONE)
        return false
    }

    /**
     * 摄像头
     */
    @JvmStatic
    fun grantedCamera(context: Context): Boolean {
        if (PermissionUtils.isGranted(CAMERA)) {
            if (useVideo == null) {
                useVideo = MediaHelper.isCameraUsable
            }
            return useVideo!!
        }
        request(context, PermissionConstants.CAMERA)
        return false
    }

    /**
     * 摄像头和录音
     */
    @JvmStatic
    fun grantedCameraAndAudio(context: Context): Boolean {
        if (PermissionUtils.isGranted(CAMERA, RECORD_AUDIO)) {
            if (useVideo == null) {
                useVideo = MediaHelper.isCameraUsable
            }
            if (useAudio == null) {
                useAudio = MediaHelper.isAudioUsable
            }
            return useVideo!! and useAudio!!
        }
        request(context, PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
        return false
    }

    /**
     * 是否允许app安装应用
     * @param activity Activity
     * @param requestCode Int 请求码
     * @return Boolean
     */
    @JvmStatic
    fun grantedInstallUnKnowApp(activity: Activity, requestCode: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canInstall = Utils.getApp().packageManager.canRequestPackageInstalls()
            if (!canInstall) {
                DialogHelper.showInstallRequestSettingDialog(activity, requestCode)
            }
            canInstall
        } else {
            true
        }
    }

    /**
     * 读取磁盘权限
     */
    @JvmStatic
    fun requestStorageInSplash(context: Context, onGranted: () -> Unit, onDenied: () -> Unit) {
        if (PermissionUtils.isGranted(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)) {
            onGranted()
            return
        }
        PermissionUtils.permission(PermissionConstants.STORAGE)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                    onGranted()
                }

                override fun onDenied(
                    permissionsDeniedForever: List<String>,
                    permissionsDenied: List<String>
                ) {
                    if (permissionsDeniedForever.isNotEmpty()) {
                        DialogHelper.showOpenAppSettingDialog(
                            context,
                            object : DialogHelper.Callback {
                                override fun onPositive() {
                                    // 这里跳转到应用设置界面了
                                    AppUtils.launchAppDetailsSettings()
                                    onDenied()
                                }

                                override fun onNegative() {
                                    onDenied()
                                }
                            })
                        return
                    } else {
                        DialogHelper.showNeedPermissionDialog(object :
                            DialogHelper.Callback {
                            override fun onPositive() {
                                requestStorageInSplash(context, onGranted, onDenied)
                            }

                            override fun onNegative() {
                                onDenied()
                            }
                        })
                    }
                }
            })
            .request()
    }

    private fun request(context: Context, @PermissionConstants.Permission vararg permissions: String) {
        PermissionUtils.permission(*permissions)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                }

                override fun onDenied(
                    permissionsDeniedForever: List<String>,
                    permissionsDenied: List<String>
                ) {
                    if (permissionsDeniedForever.isNotEmpty()) {
                        DialogHelper.showOpenAppSettingDialog(context,
                            object : DialogHelper.Callback {
                                override fun onPositive() {
                                    // 这里跳转到应用设置界面了
                                    AppUtils.launchAppDetailsSettings()
                                }

                                override fun onNegative() {}
                            })
                        return
                    } else {
                        DialogHelper.showNeedPermissionDialog(object :
                            DialogHelper.Callback {
                            override fun onPositive() {
                                request(context, *permissions)
                            }

                            override fun onNegative() {}
                        })
                    }
                }
            })
            .request()
    }
}