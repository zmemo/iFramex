package com.memo.umeng

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.memo.umeng.listener.SimpleAuthListener
import com.memo.umeng.listener.SimpleShareListener
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import java.io.File

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-14 17:36
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object UMengHelper {

    // 文档地址 https://developer.umeng.com/docs/66632/detail/66639

    /**
     * 分享文本
     * @param activity 当前Activity
     * @param platform 平台
     * @param text 文本
     */
    @JvmStatic
    fun shareText(activity: Activity, platform: SHARE_MEDIA, text: String, listener: SimpleShareListener = SimpleShareListener()) {
        ShareAction(activity)
            .withText(text)
            .setPlatform(platform)
            .setCallback(listener)
            .share()
    }

    /**
     * 分享图片
     * @param activity 当前Activity
     * @param platform 平台
     * @param text String
     * @param image 图片地址 文件File 资源文件R.drawable.ic_share_logo
     */
    @JvmStatic
    fun shareImage(activity: Activity, platform: SHARE_MEDIA, title: String, content: String, image: Any, listener: SimpleShareListener = SimpleShareListener()) {
        val umImg = when (image) {
            is String -> UMImage(activity, image)
            is File -> UMImage(activity, image)
            is Int -> UMImage(activity, image)
            else -> null
        }
        umImg?.let {
            it.title = title
            it.description = content
            ShareAction(activity)
                .withMedia(it)
                .setPlatform(platform)
                .setCallback(listener)
                .share()
        }
    }

    /**
     * 分享链接
     * @param activity 当前Activity
     * @param platform 分享平台
     * @param title 标题
     * @param content 内容
     * @param thumbImg 缩略图
     * @param url 网址
     */
    @JvmStatic
    fun shareWeb(activity: Activity, platform: SHARE_MEDIA, title: String, content: String, thumbImg: String, url: String, listener: SimpleShareListener = SimpleShareListener()) {
        val umWeb = UMWeb(url)
        umWeb.title = title
        umWeb.description = content
        umWeb.setThumb(UMImage(activity, thumbImg))
        ShareAction(activity)
            .withMedia(umWeb)
            .setPlatform(platform)
            .setCallback(listener)
            .share()
    }

    /**
     * 第三方登陆
     * @param activity 当前Activity
     * @param platform 登陆平台
     * 详细数据 https://developer.umeng.com/docs/66632/detail/66639#h2-u7528u6237u8D44u6599u57FAu672Cu4FE1u606Fu89E3u6790u5982u4E0B16
     */
    @JvmStatic
    fun login(activity: Activity, platform: SHARE_MEDIA, onSuccess: (MutableMap<String, String>) -> Unit, onError: () -> Unit) {
        UMShareAPI.get(activity).getPlatformInfo(activity, platform, object : SimpleAuthListener() {
            override fun onComplete(platform: SHARE_MEDIA, action: Int, map: MutableMap<String, String>) {
                super.onComplete(platform, action, map)
                onSuccess(map)
            }

            override fun onError(platform: SHARE_MEDIA, action: Int, error: Throwable) {
                super.onError(platform, action, error)
                onError()
            }
        })
    }

    /**
     * QQ与新浪不需要添加Activity，但需要在使用QQ分享或者授权的Activity中，onActivityResult添加：
     */
    @JvmStatic
    fun onQQAndWeiBoActivityResult(context: Context, requestCode: Int, resultCode: Int, data: Intent) {
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data)
    }


}