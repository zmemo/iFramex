package com.memo.test.ui.drag

import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.R
import com.memo.tool.ext.color
import com.memo.tool.ext.dimen
import kotlinx.android.synthetic.main.activity_drag.*


class DragActivity : BaseActivity() {

    private val dp80 = dimen(R.dimen.dp80).toInt()
    private val dp20 = dimen(R.dimen.dp20).toInt()

    private val mData = arrayListOf<DragInfo>()

    private val mAdapter = DragAdapter()


    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_drag

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        with(mData) {
            add(DragInfo("3", "张三"))
            add(DragInfo("4", "李四"))
            add(DragInfo("5", "王五"))
            add(DragInfo("6", "赵六"))
        }
    }

    private fun initView() {
        mDrawerLayout.setScrimColor(Color.TRANSPARENT)
        mData.forEach {
            val dragView = buildDrag(it)
            mFlDrag.addView(dragView)
        }
        mRvContainer.run {
            layoutManager = GridLayoutManager(mContext, 3)
            adapter = mAdapter
            val emptyView = TextView(mContext)
            emptyView.layoutParams = layoutParams
            emptyView.setPadding(0, dp20, 0, dp20)
            emptyView.text = "暂无数据"
            mAdapter.emptyView = emptyView
        }
    }

    private fun initListener() {
        mAdapter.setOnItemClickListener { _, _, position ->
            mFlDrag.addView(buildDrag(mAdapter.data[position]))
            //等待重新绘制完毕之后所有子控件重新设置位置数据
            mFlDrag.post {
                mFlDrag.children.forEach { child ->
                    (child as DragView).changeLocation()
                }
            }
            mAdapter.remove(position)
        }
    }

    private fun buildDrag(dragInfo: DragInfo): DragView {
        val dragView = DragView(mContext)
        val layoutParams = LinearLayout.LayoutParams(dp80, dp80)
        dragView.layoutParams = layoutParams
        dragView.gravity = Gravity.CENTER
        dragView.text = dragInfo.text
        dragView.setBackgroundColor(color(R.color.color_333333))
        dragView.setTextColor(Color.WHITE)
        //设置是否可以拖拽
        dragView.enableDrag(true)
        //数据内容
        dragView.setDragInfo(dragInfo)
        dragView.setOnDragListener(object : DragView.OnDragListener {
            override fun onDragUp(x: Float, y: Float) {
                if (isInner(x, y, mFlDrag.width)) {
                    mAdapter.addData(dragInfo)
                    //移除控件
                    mFlDrag.removeView(dragView)
                    //等待重新绘制完毕之后所有子控件重新设置位置数据
                    mFlDrag.post {
                        mFlDrag.children.forEach { child ->
                            (child as DragView).changeLocation()
                        }
                    }
                } else {
                    dragView.reset()
                }
                mRvContainer.setBackgroundColor(Color.WHITE)
            }

            override fun onDragMove(x: Float, y: Float) {
                if (isInner(x, y, mFlDrag.width)) {
                    mRvContainer.setBackgroundColor(Color.LTGRAY)
                } else {
                    mRvContainer.setBackgroundColor(Color.WHITE)
                }
            }

            override fun onDragReset() {
                mRvContainer.setBackgroundColor(Color.WHITE)
            }
        })

        return dragView
    }

    /**
     * 判断点是否在控件显示内部
     * @param x Float 左上角点x
     * @param y Float 左上角点y
     * @param drawerLayoutWidth Int 抽屉宽度
     * @return Boolean
     */
    fun isInner(
        x: Float,
        y: Float,
        drawerLayoutWidth: Int
    ): Boolean {
        LogUtils.iTag(
            "drag", "isInner",
            "x = $x",
            "y = $y",
            "left = ${mRvContainer.left}",
            "right = ${mRvContainer.right - drawerLayoutWidth}",
            "top = ${mRvContainer.top}",
            "bottom = ${mRvContainer.bottom}"
        )
        return x > mRvContainer.left
                && x < mRvContainer.right - drawerLayoutWidth
                && y > mRvContainer.top
                && y < mRvContainer.bottom
    }
}
