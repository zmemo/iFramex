package com.memo.base.tool.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.memo.base.tool.app.BaseApp
import com.memo.base.tool.ext.doInBackgroundExt
import com.memo.base.tool.glide.GlideApp
import com.memo.base.tool.simple.SimpleGlideTarget
import java.io.File

/**
 * title:图片加载工具
 * tip:
 *
 * @author zhou
 * @date 2018-11-13 下午8:37
 */
object ImageLoadHelper {

    /**
     * 加载图片
     * @param context Context       上下文
     * @param url Any               图片地址
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadImage(context: Context, url: Any?, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载图片
     * @param context Context       上下文
     * @param url Any               图片地址
     * @param holderRes Int         站位图
     * @param errorRes Int          错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadImage(context: Context, url: Any?, @DrawableRes holderRes: Int, @DrawableRes errorRes: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载圆角图片
     * @param context Context       上下文
     * @param url Any               图片地址
     * @param radius Int            圆角
     * @param holderRes Int         占位图
     * @param errorRes Int          错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadRoundImage(context: Context, url: Any?, radius: Int, @DrawableRes holderRes: Int, @DrawableRes errorRes: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(imageView)
    }

    /**
     * 加载圆形图片
     * @param context Context       上下文
     * @param url Any               图片地址
     * @param holderRes Int         占位图
     * @param errorRes Int          错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadCircleImage(context: Context, url: Any, @DrawableRes holderRes: Int, @DrawableRes errorRes: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transform(CenterCrop(), CircleCrop())
            .into(imageView)
    }

    /**
     * 加载图片监听
     * @param context Context               上下文
     * @param url Any                       图片地址
     * @param onSuccess (Bitmap?) -> Unit   成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun loadImageListener(context: Context, url: Any?,
                          onSuccess: (Bitmap?) -> Unit,
                          onFailure: () -> Unit) {
        GlideApp.with(context)
            .asBitmap()
            .load(url)
            .centerCrop()
            .into(object : SimpleGlideTarget() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onSuccess(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    onFailure()
                }
            })
    }

    /**
     * 加载图片监听
     * @param context Context               上下文
     * @param url Any                       图片地址
     * @param width Int                     宽
     * @param height Int                    高
     * @param onSuccess (Bitmap?) -> Unit   成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun loadResizeCircleImageListener(context: Context, url: Any, width: Int, height: Int,
                                      onSuccess: (Bitmap?) -> Unit, onFailure: () -> Unit) {
        GlideApp.with(context)
            .asBitmap()
            .load(url)
            .transform(CenterCrop(), CircleCrop())
            .into(object : SimpleGlideTarget(width, height) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onSuccess(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    onFailure()
                }
            })
    }

    /**
     * 从缓存中获取bitmap
     * @param context           当前上下文
     * @param lifecycleOwner    生命周期控制
     * @param url               地址
     * @param onSuccess         成功回调
     * @param onFailure         失败回调
     */
    @JvmStatic
    fun getImageFileFromCache(context: Context, lifecycleOwner: LifecycleOwner, url: String,
                              onSuccess: (file: File) -> Unit,
                              onFailure: (error: Throwable) -> Unit) {
        doInBackgroundExt(lifecycleOwner, {
            GlideApp
                .with(context)
                .downloadOnly()
                .load(url)
                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get()
        }, onSuccess, onFailure)
    }

    /**
     * 暂停加载
     */
    @JvmStatic
    fun onPause(context: Context) {
        GlideApp.with(context).pauseRequests()
    }

    /**
     * 恢复加载
     */
    @JvmStatic
    fun onResume(context: Context) {
        GlideApp.with(context).resumeRequests()
    }

    /**
     * 停止加载
     */
    @JvmStatic
    fun onStop(context: Context) {
        Glide.with(context).onStop()
    }

    /**
     * 清除磁盘缓存
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun clearDiskCache(owner: LifecycleOwner) {
        doInBackgroundExt(owner) {
            GlideApp.get(BaseApp.app.applicationContext).clearDiskCache()
        }
    }

    /**
     * 清除内存缓存
     */
    @JvmStatic
    fun clearMemoryCache() {
        GlideApp.get(BaseApp.app.applicationContext).clearMemory()
    }
}
