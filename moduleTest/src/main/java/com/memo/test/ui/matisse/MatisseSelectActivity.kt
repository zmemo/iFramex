package com.memo.test.ui.matisse

import android.app.Activity
import android.content.Intent
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.dir.LocalDir
import com.memo.tool.ext.onClick
import com.memo.tool.helper.MediaHelper
import com.memo.tool.helper.PermissionHelper
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.activity_matisse_select.*

class MatisseSelectActivity : BaseActivity() {

    private val REQUEST_CODE_SELECT: Int = 1
    private val REQUEST_CODE_TAKE: Int = 2
    private val REQUEST_CODE_CROP: Int = 3
    private val REQUEST_CODE_VIDEO: Int = 4

    private val MAXSIZE: Int = 9
    private var takePhotoPath: String? = null
    private var cropPhotpPath: String? = null

    override fun bindLayoutResId(): Int = R.layout.activity_matisse_select

    override fun initialize() {
        initView()
        initListener()
    }

    private fun initView() {
        mNineGridView
            .setAddDrawableRes(R.drawable.ic_pic_add)
            .setDelDrawableRes(R.drawable.ic_pic_del)
            .setMaxImageSize(MAXSIZE)
            .initialize()
    }

    private fun initListener() {
        mNineGridView.addOnSelectListener {
            MediaHelper.choosePhoto(
                mActivity,
                false,
                MAXSIZE - mNineGridView.getCurImageSize(),
                REQUEST_CODE_SELECT
            )
        }
        mBtnSelect.onClick {
            MediaHelper.choosePhoto(
                mActivity,
                false,
                MAXSIZE - mNineGridView.getCurImageSize(),
                REQUEST_CODE_SELECT
            )
        }
        mBtnTake.onClick {
            if (PermissionHelper.grantedCamera(mContext)) {
                takePhotoPath = MediaHelper.takePhoto(mActivity, REQUEST_CODE_TAKE)
            }
        }
        mBtnVideo.onClick {
            MediaHelper.chooseVideo(mActivity, REQUEST_CODE_VIDEO)
        }
        mBtnClear.onClick {
            FileUtils.deleteDir(LocalDir.DIR_COMPRESS)
            FileUtils.deleteDir(LocalDir.DIR_CROP)
            FileUtils.deleteDir(LocalDir.DIR_CAPTURE)
            mNineGridView.clear()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_SELECT -> {
                    if (data != null) {
                        val pathList = MediaHelper.obtainPathResult(data)
                        // 压缩图片并且添加
                        mLoadDialog.show("图片压缩")
                        MediaHelper.compressImagesASyn(mContext, pathList, { it ->
                            val builder = StringBuilder()
                            it.forEach {
                                builder.append(it.name)
                                    .append(" ")
                                    .append(FileUtils.getFileSize(it))
                                    .append("\n")
                            }
                            LogUtils.iTag("compress", builder)

                            mNineGridView.addImageFiles(it)
                            toast("图片压缩完毕")
                            mLoadDialog.dismiss()
                        }, {
                            toast("图片压缩失败")
                            mLoadDialog.dismiss()
                        })
                    }
                }
                REQUEST_CODE_TAKE -> {
                    takePhotoPath?.let {
                        cropPhotpPath = MediaHelper.cropPhoto(mActivity, it, REQUEST_CODE_CROP)
                    }
                }
                REQUEST_CODE_CROP -> {
                    cropPhotpPath?.let {
                        mNineGridView.addImages(it)
                        toast("图片裁剪完毕")
                    }
                }
                REQUEST_CODE_VIDEO -> {
                    val videoPath = MediaHelper.obtainPathResult(data)
                    LogUtils.iTag("Matisse", videoPath)
                }
            }
        }
    }
}
