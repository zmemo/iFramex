package com.memo.tool.preview

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.memo.tool.glide.GlideApp
import com.memo.tool.utils.ImageLoadHelper
import com.previewlibrary.loader.IZoomMediaLoader
import com.previewlibrary.loader.MySimpleTarget

/**
 * title:大图预览加载器
 * describe:
 *
 * @author zhou
 * @date 2019-01-31 17:13
 */
class PreviewImageLoader(@DrawableRes val errorRes: Int) : IZoomMediaLoader {


    override fun displayImage(
        context: Fragment,
        path: String,
        imageView: ImageView,
        simpleTarget: MySimpleTarget
    ) {
        GlideApp.with(context)
            .asBitmap()
            .load(path)
            .error(errorRes)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Bitmap>,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onLoadFailed(null)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any,
                    target: Target<Bitmap>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onResourceReady()
                    return false
                }
            })
            .into(imageView)
    }

    override fun displayGifImage(
        context: Fragment,
        path: String,
        imageView: ImageView,
        simpleTarget: MySimpleTarget
    ) {
        GlideApp.with(context)
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(path)
            .error(errorRes)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onLoadFailed(null)
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable,
                    model: Any,
                    target: Target<GifDrawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    simpleTarget.onResourceReady()
                    return false
                }
            })
            .into(imageView)
    }

    override fun onStop(fragment: Fragment) {
        ImageLoadHelper.onStop(fragment.activity!!)
    }

    override fun clearMemory(context: Context) {
        ImageLoadHelper.clearMemoryCache(context)
    }
}
