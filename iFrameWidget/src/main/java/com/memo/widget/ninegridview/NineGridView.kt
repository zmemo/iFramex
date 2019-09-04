package com.memo.widget.ninegridview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.memo.tool.ext.inflaterView
import com.memo.tool.helper.toast
import com.memo.tool.preview.ImagePreviewHelper
import com.memo.widget.R
import com.memo.widget.recyclerview.NoScrollGridLayoutManager
import kotlinx.android.synthetic.main.layout_nine_grid_view.view.*
import java.io.File

/**
 * title:九宫格
 * describe:内部逻辑谨慎更改 注意测试
 *
 * @author zhou
 * @date 2019-06-05 15:54
 */
class NineGridView : FrameLayout {

    private val mGridLayoutManager: NoScrollGridLayoutManager by lazy { NoScrollGridLayoutManager(context, 3) }
    private val mAdapter: NineGridViewAdapter by lazy { NineGridViewAdapter() }
    private var mListener: OnClickListener? = null
    private var mMaxImageSize: Int = 9
    @DrawableRes
    private var mAddDrawableRes: Int = R.drawable.ic_pic_add
    @DrawableRes
    private var mDelDrawableRes: Int = R.drawable.ic_pic_del

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        inflaterView(R.layout.layout_nine_grid_view, this)
        initView()
        initListener()
    }

    private fun initView() {
        mRvImage.run {
            layoutManager = mGridLayoutManager
            mAdapter.bindToRecyclerView(mRvImage)
            adapter = mAdapter
        }
    }

    private fun initListener() {
        mAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.mIvPic -> {
                    if (mAdapter.data[position] == mAddDrawableRes) {
                        // 添加图片
                        mListener?.onSelectPicture()
                    } else {
                        // 跳转预览大图
                        if (context is Activity) {
                            ImagePreviewHelper.start(context as Activity, mGridLayoutManager, R.id.mIvPic, getImgList(), position)
                        }
                    }
                }
                R.id.mIvDel -> {
                    // 删除图片
                    mAdapter.remove(position)
                    if (getCurImageSize() < mMaxImageSize) {
                        addAdd()
                    }
                }
            }
        }
    }

    /**
     * 设置图片最大添加数量
     * @param max Int 最大限制
     * @return NineGridView
     */
    fun setMaxImageSize(max: Int): NineGridView {
        mMaxImageSize = max
        return this
    }

    /**
     * 设置添加图片的资源文件
     * @param addRes Int 添加图标
     * @return NineGridView
     */
    fun setAddDrawableRes(@DrawableRes addRes: Int): NineGridView {
        mAddDrawableRes = addRes
        return this
    }

    /**
     * 设置删除图片的资源文件
     * @param delRes Int 删除图标
     * @return NineGridView
     */
    fun setDelDrawableRes(@DrawableRes delRes: Int): NineGridView {
        mDelDrawableRes = delRes
        return this
    }

    /**
     * 初始化
     */
    fun initialize() {
        mAdapter.setDrawableRes(mAddDrawableRes, mDelDrawableRes)
        addAdd()
    }

    /**
     * 添加一组图片
     * @param images MutableList<String> 图片列表
     */
    fun addImages(images: List<String>) {
        if (getCurImageSize() + images.size > mMaxImageSize) {
            toast("选择图片超出最大限制")
            return
        }
        addImage(images)
    }

    /**
     * 添加图片 主要用于一张图片
     * @param images Array<out String> 图片
     */
    fun addImages(vararg images: String) {
        addImages(images.asList())
    }

    /**
     * 添加图片
     * @param images List<File> 图片列表
     */
    fun addImageFiles(images: List<File>) {
        addImages(images.map {
            it.absolutePath
        })
    }

    /**
     * 清空所有图片
     * 添加Add图片
     */
    fun clear() {
        mAdapter.data.clear()
        addAdd()
    }

    /**
     * 设置选择图片的监听
     * @param onSelectPicture () -> Unit 选择图片监听
     * @return NineGridView
     */
    fun addOnSelectListener(onSelectPicture: () -> Unit): NineGridView {
        mListener = object : OnClickListener {
            override fun onSelectPicture() {
                onSelectPicture()
            }
        }
        return this
    }

    /**
     * 获取当前选中的图片
     * @return ArrayList<String> 图片列表
     */
    fun getImgList(): ArrayList<String> {
        val images: ArrayList<String> = arrayListOf()
        for (path in mAdapter.data) {
            if (path != mAddDrawableRes && path is String) {
                images.add(path)
            }
        }
        return images
    }

    /**
     * 实际选中图片的数量
     * @return Int
     */
    fun getCurImageSize(): Int {
        var size = 0
        for (path in mAdapter.data) {
            if (path != mAddDrawableRes) {
                size++
            }
        }
        return size
    }

    private fun addImage(images: List<String>) {
        val curImageSize: Int = getCurImageSize()
        val allSize = images.size
        if (curImageSize + allSize < mMaxImageSize) {
            mAdapter.addData(curImageSize, images)
        } else {
            mAdapter.remove(curImageSize)
            mAdapter.addData(images)
        }
    }

    /**
     * 添加一个加号图片
     */
    private fun addAdd() {
        // 只有在数量小于mMaxImageSize的时候才添加加号
        if (mAdapter.data.size < mMaxImageSize) {
            if (mAdapter.data.size == 0) {
                mAdapter.addData(mAddDrawableRes)
            } else {
                if (mAdapter.data.last() != mAddDrawableRes) {
                    mAdapter.addData(mAddDrawableRes)
                }
            }
        }
    }

    /**
     * 删除末尾的加号图片
     */
    private fun removeAdd() {
        // 判断最后一个是不是加号
        if (mAdapter.data.size > 0 && mAdapter.data.last() == mAddDrawableRes) {
            mAdapter.remove(mAdapter.data.lastIndex)
        }
    }

    interface OnClickListener {
        fun onSelectPicture()
    }
}