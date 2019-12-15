package com.memo.widget.refreshlist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.widget.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.layout_refresh_list.view.*

/**
 * title:刷新列表
 * describe:
 *
 * @author memo
 * @date 2019-12-13 17:37
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RefreshList : FrameLayout {
	
	private var mRefreshLayout : SmartRefreshLayout
	private var mRvList : RecyclerView
	
	constructor(context : Context)
			: this(context, null)
	
	constructor(context : Context, attrs : AttributeSet?)
			: this(context, attrs, 0)
	
	constructor(context : Context, attrs : AttributeSet?, defStyleAttr : Int)
			: super(context, attrs, defStyleAttr) {
		val mRootView = LayoutInflater.from(context).inflate(R.layout.layout_refresh_list, this)
		mRefreshLayout = mRootView.mRefreshLayout
		mRvList = mRootView.mRvList
		mRvList.layoutManager = LinearLayoutManager(context)
	}
	
	
	//---------------------------------------- SmartRefreshLayout -------------------------------------
	
	fun getRefreshLayout() = mRefreshLayout
	
	/**
	 * 设置刷新加载监听
	 * @param onRefresh 刷新方法
	 * @param onLoadMore 加载方法
	 */
	fun setOnRefreshAndLoadMoreListener(onRefresh : () -> Unit, onLoadMore : () -> Unit) {
		mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
			override fun onLoadMore(refreshLayout : RefreshLayout) {
				onLoadMore()
			}
			
			override fun onRefresh(refreshLayout : RefreshLayout) {
				onRefresh()
			}
		})
	}
	
	/**
	 * 设置刷新方法
	 * @param onRefresh 刷新方法
	 */
	fun setOnRefreshListener(onRefresh : () -> Unit) {
		setOnRefreshAndLoadMoreListener(onRefresh, {})
	}
	
	/**
	 * 设置加载方法
	 * @param onLoadMore 加载方法
	 */
	fun setOnLoadMoreListener(onLoadMore : () -> Unit) {
		setOnRefreshAndLoadMoreListener({}, onLoadMore)
	}
	
	/**
	 * 停止刷新
	 * @param hasMore 是否有更多数据
	 */
	fun finish(hasMore : Boolean = true) {
		mRefreshLayout.setEnableLoadMore(hasMore)
		if (mRefreshLayout.state == RefreshState.Loading) {
			mRefreshLayout.finishLoadMore(400)
		} else if (mRefreshLayout.state == RefreshState.Refreshing) {
			mRefreshLayout.finishRefresh(400)
		}
	}
	
	
	//---------------------------------------- RecyclerView ----------------------------------------
	
	fun getRecyclerView() = mRvList
	
	/**
	 * 设置适配器
	 * @param adapter 适配器
	 */
	fun setAdapter(adapter : RecyclerView.Adapter<ViewHolder>) {
		mRvList.adapter = adapter
	}
	
}