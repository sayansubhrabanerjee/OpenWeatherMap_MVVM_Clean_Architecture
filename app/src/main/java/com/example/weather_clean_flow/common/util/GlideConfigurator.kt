package com.example.weather_clean_flow.common.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions

fun configureGlide(context: Context, url: String, imageView: ImageView) {
    GlideApp.with(context)
        .applyDefaultRequestOptions(requestOptions)
        .load(url)
        .into(imageView)
}

private val requestOptions: RequestOptions =
    RequestOptions()
        //.override(200, 200)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .downsample(DownsampleStrategy.AT_LEAST)