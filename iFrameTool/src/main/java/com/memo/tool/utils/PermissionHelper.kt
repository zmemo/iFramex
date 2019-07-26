package com.memo.tool.utils

import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.PermissionUtils

/**
 * title:权限请求工具
 * describe:
 *
 * @author zhou
 * @date 2019-01-30 17:25
 */
object PermissionHelper {

    private const val CAMERA = android.Manifest.permission.CAMERA
    private const val RECORD_AUDIO = android.Manifest.permission.RECORD_AUDIO
    private const val WRITE_EXTERNAL_STORAGE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private const val READ_EXTERNAL_STORAGE = android.Manifest.permission.READ_EXTERNAL_STORAGE


    /**
     * 存储
     */
    @JvmStatic
    fun grantedStorage(): Boolean {
        if (PermissionUtils.isGranted(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)) {
            return true
        }
        request(PermissionConstants.STORAGE)
        return false
    }

    /**
     * 摄像头
     */
    @JvmStatic
    fun grantedCamera(): Boolean {
        if (PermissionUtils.isGranted(CAMERA)) {
            return true
        }
        request(PermissionConstants.CAMERA)
        return false
    }

    /**
     * 摄像头和录音
     */
    @JvmStatic
    fun grantedCameraAndAudio(): Boolean {
        if (PermissionUtils.isGranted(CAMERA, RECORD_AUDIO)) {
            return true
        }
        request(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
        return false
    }

    /**
     * 读取磁盘权限
     */
    @JvmStatic
    fun requestStorageInSplash(onGranted: () -> Unit, onDenied: () -> Unit) {
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
                        DialogHelper.showOpenAppSettingDialog(object :
                            DialogHelper.Callback {
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
                                requestStorageInSplash(onGranted, onDenied)
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

    private fun request(@PermissionConstants.Permission vararg permissions: String) {
        PermissionUtils.permission(*permissions)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                }

                override fun onDenied(
                    permissionsDeniedForever: List<String>,
                    permissionsDenied: List<String>
                ) {
                    if (permissionsDeniedForever.isNotEmpty()) {
                        DialogHelper.showOpenAppSettingDialog(object : DialogHelper.Callback {
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
                                request(*permissions)
                            }

                            override fun onNegative() {}
                        })
                    }
                }
            })
            .request()
    }
}