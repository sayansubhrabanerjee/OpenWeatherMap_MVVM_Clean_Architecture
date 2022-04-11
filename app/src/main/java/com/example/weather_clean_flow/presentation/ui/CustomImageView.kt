package com.example.weather_clean_flow.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.core.content.withStyledAttributes
import com.example.weather_clean_flow.R
import com.example.weather_clean_flow.databinding.LayoutCustomImageviewBinding

class CustomImageView(
    context: Context,
    attrs: AttributeSet
) : ImageView(context, attrs) {

    private var binding: LayoutCustomImageviewBinding? = null

    init {
        binding = LayoutCustomImageviewBinding.inflate(LayoutInflater.from(context))

        context.withStyledAttributes(attrs, R.styleable.CustomImageView) {
            binding?.imageViewIcon?.setImageDrawable(getDrawable(R.styleable.CustomImageView_imageRef))
        }
    }
}