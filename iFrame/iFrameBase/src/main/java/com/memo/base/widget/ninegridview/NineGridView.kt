package com.memo.base.widget.ninegridview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.memo.base.R
import com.memo.base.tool.ext.inflaterView
import com.memo.base.tool.ext.toast
import com.memo.base.tool.preview.ImagePreviewHelper
import com.memo.base.widget.recyclerview.layoutmanager.NoScrollGridLayoutManager
import kotlinx.android.synthetic.main.layout_nine_grid_view.view.*
import java.io.File

/**
 * title:九宫格
 * describe:内部逻辑谨慎更改 注意测试
 *
 * @author zhou
 * @date 2019-06-05 15:54
 */
class NineGridView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

	private val mGridLayoutManager: NoScrollGridLayoutManager by lazy { NoScrollGridLayoutManager(context, 3) }

	/*** 自定义九宫格适配器 ***/
	private val mAdapter: NineGridViewAdapter by lazy { NineGridViewAdapter() }

	/*** 点击加号监听 ***/
	private var mSelectListener: OnSelectListener? = null

	/*** 最大图片展示 ***/
	private var mMaxImageSize: Int = 9

	@DrawableRes
	private var mAddDrawableRes: Int = R.drawable.ic_pic_add

	@DrawableRes
	private var mDelDrawableRes: Int = R.drawable.ic_pic_del


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
						mSelectListener?.onSelectPicture(mMaxImageSize - getCurImageSize())
					} else {
						// 跳转预览大图
						if (context is Activity) {
							ImagePreviewHelper.start(
								context as Activity,
								mGridLayoutManager,
								R.id.mIvPic,
								getCurImageList(),
								position
							)
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
		addImages(images.map { it.absolutePath })
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
	fun addOnSelectListener(onSelectPicture: (leftSize: Int) -> Unit): NineGridView {
		mSelectListener = object : OnSelectListener {
			override fun onSelectPicture(leftSize: Int) {
				onSelectPicture(leftSize)
			}
		}
		return this
	}

	/**
	 * 获取当前选中的图片
	 * @return ArrayList<String> 图片列表
	 */
	fun getCurImageList(): ArrayList<String> {
		val images: ArrayList<String> = arrayListOf()
		for (path in mAdapter.data) {
			if (path != mAddDrawableRes && path is String && path.isNotEmpty()) {
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
		return getCurImageList().size
	}

	private fun addImage(images: List<String>) {
		val curImageSize: Int = getCurImageSize()
		val addImageSize = images.size
		if (curImageSize + addImageSize < mMaxImageSize) {
			// 在加号之前放入图片
			mAdapter.addData(curImageSize, images)
		} else {
			// 去除加号 添加图片
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

	/*** 点击加号的监听 ***/
	interface OnSelectListener {
		/**
		 * 选择图片
		 * @param leftSize 剩余可选择图片数量
		 */
		fun onSelectPicture(leftSize: Int)
	}
}