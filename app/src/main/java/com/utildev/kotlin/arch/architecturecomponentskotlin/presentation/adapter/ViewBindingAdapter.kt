package com.utildev.kotlin.arch.architecturecomponentskotlin.presentation.adapter

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

private val requestOptions = RequestOptions().transform(CircleCrop())

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl", "progressBar")
fun loadImage(imageView: ImageView, url: String, progressBar: ProgressBar) {
    Glide.with(imageView.context)
        .load(url)
        .apply(requestOptions)
        .listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?, model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .into(imageView)
}