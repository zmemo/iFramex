package com.memo.base.tool.photo

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zhihu.matisse.engine.ImageEngine

/**
 * title:GlideEngine For Matisse
 * describe:
 *
 * @author zhou
 * @date 2019-05-06 13:48
 */
class Glide4Engine : ImageEngine {

    override fun loadThumbnail(
        context: Context,
        resize: Int,
        placeholder: Drawable,
        imageView: ImageView,
        uri: Uri
    ) {
        Glide.with(context)
            .asBitmap() // some .jpeg files are actually gif
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(
                RequestOptions()
                    .override(resize, resize)
                    .placeholder(placeholder)
                    .centerCrop()
            )
            .into(imageView)
    }

    override fun loadGifThumbnail(
        context: Context,
        resize: Int,
        placeholder: Drawable,
        imageView: ImageView,
        uri: Uri
    ) {
        Glide.with(context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(uri)
            .apply(
                RequestOptions()
                    .override(resize, resize)
                    .placeholder(placeholder)
                    .centerCrop()
            )
            .into(imageView)
    }

    override fun loadImage(
        context: Context,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView,
        uri: Uri
    ) {
        Glide.with(context)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(
                RequestOptions()
                    .override(resizeX, resizeY)
                    .priority(Priority.HIGH)
                    .centerCrop()
            )
            .into(imageView)
    }

    override fun loadGifImage(
        context: Context,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView,
        uri: Uri
    ) {
        Glide.with(context)
            .asGif()
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(
                RequestOptions()
                    .override(resizeX, resizeY)
                    .priority(Priority.HIGH)
                    .centerCrop()
            )
            .into(imageView)
    }

    override fun supportAnimatedGif(): Boolean {
        return true
    }
}