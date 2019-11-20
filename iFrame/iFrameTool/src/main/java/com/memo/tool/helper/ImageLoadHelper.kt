package com.memo.tool.helper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.memo.tool.app.BaseApp
import com.memo.tool.ext.doInBackground
import com.memo.tool.ext.io2Main
import com.memo.tool.glide.GlideApp
import io.reactivex.Observable
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
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param image ImageView   图片
     */
    @JvmStatic
    fun loadImage(context: Context, url: Any, image: ImageView) {
        GlideApp.with(context)
            .load(url)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载无缓存图片
     * @param context Context     上下文
     * @param url Any         地址
     * @param image ImageView   图片
     */
    @JvmStatic
    fun loadNoCacheImage(context: Context, url: Any, image: ImageView) {
        GlideApp.with(context)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .into(image)
    }

    /**
     * 加载不需要内粗缓存的图片
     * @param context Context
     * @param url Any
     * @param imageView ImageView
     */
    @JvmStatic
    fun loadNoMemoryImage(context: Context, url: Any, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .skipMemoryCache(true)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载不需要磁盘缓存的图片
     * @param context Context
     * @param url Any
     * @param imageView ImageView
     */
    @JvmStatic
    fun loadNoDiskImage(context: Context, url: Any, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param holderRes Int         站位图
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadImage(
        context: Context,
        url: Any,
        @DrawableRes holderRes: Int,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadImage(
        context: Context,
        url: Any,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .error(errorRes)
            .centerCrop()
            .into(imageView)
    }

    /**
     * 加载自定义宽高图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param width Int         宽
     * @param height Int         高
     * @param holderRes Int         占位图
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadResizeImage(
        context: Context,
        url: Any,
        width: Int,
        height: Int,
        @DrawableRes holderRes: Int,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .centerCrop()
            .override(width, height)
            .into(imageView)
    }

    /**
     * 加载圆角图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param radius Int         圆角
     * @param holderRes Int         占位图
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadRoundImage(
        context: Context,
        url: Any,
        radius: Int,
        @DrawableRes holderRes: Int,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(imageView)
    }

    /**
     * 加载圆角图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param radius Int         圆角
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadRoundImage(
        context: Context,
        url: Any,
        radius: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(imageView)
    }

    /**
     * 加载圆角缩略图
     * @param context Context     上下文
     * @param url Any         地址
     * @param radius Int         圆角
     * @param thumbnail Float       缩略比例
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadRoundThumbImage(
        context: Context,
        url: Any,
        radius: Int,
        thumbnail: Float,
        errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .thumbnail(thumbnail)
            .error(errorRes)
            .transform(CenterCrop(), RoundedCorners(radius))
            .into(imageView)
    }

    /**
     * 加载圆形图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param holderRes Int         占位图
     * @param errorRes Int         错误图
     * @param imageView ImageView   图片
     */
    @JvmStatic
    fun loadCircleImage(
        context: Context,
        url: Any,
        @DrawableRes holderRes: Int,
        @DrawableRes errorRes: Int,
        imageView: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .transform(CenterCrop(), CircleCrop())
            .into(imageView)
    }

    /**
     * 加载自定义大小圆形图片
     * @param context Context     上下文
     * @param url Any         图片地址
     * @param size Int         大小
     * @param holderRes Int         占位图
     * @param errorRes Int         错误图
     * @param image ImageView   图片
     */
    @JvmStatic
    fun loadResizeCircleImage(
        context: Context,
        url: Any,
        size: Int,
        @DrawableRes holderRes: Int,
        @DrawableRes errorRes: Int,
        image: ImageView
    ) {
        GlideApp.with(context)
            .load(url)
            .placeholder(holderRes)
            .error(errorRes)
            .override(size)
            .transform(CenterCrop(), CircleCrop())
            .into(image)
    }

    /**
     * 从缓存中获取bitmap
     * @param context Context 上下文
     * @param url String 图片路径
     * @return Disposable?  注意回收
     */
    @JvmStatic
    fun getCacheImageFile(
        context: Context,
        url: String
    ): Observable<File> {
        return Observable.just(url)
            .map {
                GlideApp
                    .with(context)
                    .downloadOnly()
                    .load(it)
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get()
            }.io2Main()
    }

    /**
     * 加载图片监听
     * @param context Context             上下文
     * @param url Any                 图片地址
     * @param onSuccess (Bitmap?) -> Unit   成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun loadImageListener(
        context: Context,
        url: Any,
        imageView: ImageView,
        onSuccess: (Bitmap?) -> Unit,
        onFailure: () -> Unit
    ) {
        GlideApp.with(context)
            .asBitmap()
            .load(url)
            .centerCrop()
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onFailure()
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(resource)
                    return false
                }
            }).into(imageView)
    }

    /**
     * 加载图片监听
     * @param context Context             上下文
     * @param url Any                 图片地址
     * @param width Int                 宽
     * @param height Int                 高
     * @param onSuccess (Bitmap?) -> Unit   成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun loadResizeImageListener(
        context: Context,
        url: Any,
        width: Int,
        height: Int,
        imageView: ImageView,
        onSuccess: (Bitmap?) -> Unit,
        onFailure: () -> Unit
    ) {
        GlideApp.with(context)
            .asBitmap()
            .load(url)
            .override(width, height)
            .centerCrop()
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onFailure()
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(resource)
                    return false
                }
            }).into(imageView)
    }

    /**
     * 加载图片监听
     * @param context Context             上下文
     * @param url Any                 图片地址
     * @param width Int                 宽
     * @param height Int                 高
     * @param onSuccess (Bitmap?) -> Unit   成功回调
     * @param onFailure () -> Unit          失败回调
     */
    @SuppressLint("CheckResult")
    @JvmStatic
    fun loadResizeCircleImageListener(
        context: Context,
        url: Any,
        width: Int,
        height: Int,
        imageView: ImageView,
        onSuccess: (Bitmap?) -> Unit,
        onFailure: () -> Unit
    ) {
        GlideApp.with(context)
            .asBitmap()
            .load(url)
            .transform(CenterCrop(), CircleCrop())
            .override(width, height)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onFailure()
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(resource)
                    return false
                }
            }).into(imageView)
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
     * 暂停并且停止加载
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
        doInBackground(owner) {
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
