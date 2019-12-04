package com.memo.base.manager.init

import android.app.Application
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.memo.base.BuildConfig
import com.memo.base.R
import com.memo.base.config.config.Config
import com.memo.base.config.constant.Constant
import com.memo.umeng.UMengManager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-30 09:59
 */
class InitManager {
	
	private var isInitInApp = false
	
	private var isInitInSplash = false
	
	private object Holder {
		val instance = InitManager()
	}
	
	companion object {
		fun get() = Holder.instance
	}
	
	/**
	 * 在App中进行初始化
	 * @param app Application
	 */
	fun initInApp(app : Application) {
		if (!isInitInApp && ProcessUtils.isMainProcess()) {
			// 哆啦A梦插件初始化
			DoraemonKit.install(app)
			DoraemonKit.setDebug(BuildConfig.DEBUG)
			
			// 初始化MMKV
			MMKV.initialize(app)
			MMKV.setLogLevel(MMKVLogLevel.LevelNone)
			
			// 初始化AndroidUtilCode
			Utils.init(app)
			// 初始化日志打印
			LogUtils.getConfig()
				.setLogSwitch(Config.isOpenLog)
				.globalTag = "iFrame"
			
			
			// 初始化ARouter
			if (BuildConfig.DEBUG) {
				ARouter.openLog()
				ARouter.openDebug()
			}
			ARouter.init(app)
			
			//这里缺少注册QQ 微信等
			UMengManager.get().init(app, Constant.AppKey.UMengAppKey)
				.registerQQ(Constant.AppKey.QQAppId, Constant.AppKey.QQAppKey)
			
			LogUtils.iTag("init", "Application中初始化完毕")
			isInitInApp = true
		}
	}
	
	fun initInSplash() {
		if (!isInitInSplash) {
			//初始化刷新框架
			//初始化刷新框架
			SmartRefreshLayout.setDefaultRefreshInitializer { _, refreshLayout ->
				refreshLayout
					.setEnableAutoLoadMore(false)
					.setEnableOverScrollBounce(true)
					.setEnableOverScrollDrag(true)
					.setEnableLoadMoreWhenContentNotFull(false)
			}
			SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
				ClassicsHeader(context)
			}
			SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
				BallPulseFooter(context)
					.setNormalColor(ContextCompat.getColor(context, R.color.color_666666))
					.setAnimatingColor(ContextCompat.getColor(context, R.color.color_666666))
			}
			LogUtils.iTag("init", "启动页中初始化完毕")
			isInitInSplash = true
		}
	}
	
}