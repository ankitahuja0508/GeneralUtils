package com.aexyn.generalutils.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {
    companion object {
        @BindingAdapter("imageIcon")
        @JvmStatic
        fun imageIcon(view: ImageView, icon:Int) {
            view.setImageResource(icon)
        }

        @BindingAdapter("loadImage")
        @JvmStatic
        fun loadImage(view: ImageView, any: Any?) {
            if(any != null)
                Glide.with(view.context)
                        .load(any)
                        .centerCrop()
                        .into(view)
            else
                view.setImageResource(0)
        }
    }
}