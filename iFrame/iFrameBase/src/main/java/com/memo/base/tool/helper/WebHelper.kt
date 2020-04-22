package com.memo.base.tool.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebConfig
import com.just.agentweb.WebViewClient

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-08 14:10
 */
object WebHelper {

	@SuppressLint("SetJavaScriptEnabled", "ObsoleteSdkInt")
	fun init(activity: Activity, container: ViewGroup, @LayoutRes errorLayoutRes: Int, url: String, onWebFinish: () -> Unit = {}): AgentWeb {
		//如果是空白网址 设置一个错误的地址
		val httpUrl = if (url.isEmpty()) "https://error" else url

		val agentWeb = AgentWeb.with(activity)
				.setAgentWebParent(container, FrameLayout.LayoutParams(-1, -1))
				.useDefaultIndicator()
				.setWebViewClient(object : WebViewClient() {
					override fun onPageFinished(view: WebView?, url: String?) {
						super.onPageFinished(view, url)
						onWebFinish()
					}
				})
				.setMainFrameErrorView(errorLayoutRes, -1)
				.createAgentWeb()
				.ready()
				.go(httpUrl)

		val webView = agentWeb.webCreator.webView
		val settings = webView.settings
		//去除过度拉伸效果
		webView.overScrollMode = WebView.OVER_SCROLL_NEVER
		//适应屏幕
		settings.loadWithOverviewMode = true
		//启动双指放大
		settings.setSupportZoom(true)
		settings.builtInZoomControls = true
		settings.displayZoomControls = false
		//允许js交互
		settings.javaScriptEnabled = true
		//适配5.0不允许http和https混合使用情况
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
			webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
		} else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
		}
		return agentWeb
	}

	/**
	 * 生命周期 onPause
	 */
	fun onPause(agentWeb: AgentWeb?) {
		agentWeb?.webLifeCycle?.onPause()
	}

	/**
	 * 生命周期 onResume
	 */
	fun onResume(agentWeb: AgentWeb?) {
		agentWeb?.webLifeCycle?.onResume()
	}

	/**
	 * 生命周期 onDestroy
	 */
	fun onDestroy(agentWeb: AgentWeb?) {
		agentWeb?.webLifeCycle?.onDestroy()
	}

	/**
	 * 清除本地缓存
	 */
	fun clearCache(context: Context) {
		AgentWebConfig.clearDiskCache(context)
	}

	/**
	 * android调用网页js方法
	 * @param agentWeb AgentWeb
	 * @param method 方法名
	 * @param callback 方法回调
	 * @param params 传参
	 */
	fun callJs(
			agentWeb: AgentWeb?,
			method: String,
			callback: ValueCallback<String>? = null,
			vararg params: String
	) {
		agentWeb?.jsAccessEntrace?.quickCallJs(method, callback, *params)
	}

	/**
	 * android响应网页js方法
	 * 例如：网页调方法 window.android.showToast(message)
	 * className = android
	 * method类 必须有showToast(message:String)方法 并且方法添加@JavascriptInterface注解
	 *
	 * @param agentWeb AgentWeb
	 * @param className 方法名
	 * @param methodClazz Any
	 *
	 */
	fun respondJs(agentWeb: AgentWeb?, className: String, methodClazz: Any) {
		agentWeb?.jsInterfaceHolder?.addJavaObject(className, methodClazz)
	}


}