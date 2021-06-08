package com.aexyn.generalutils.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {
    companion object {

        @BindingAdapter("loadImageOrIcon")
        @JvmStatic
        fun loadImageOrIcon(view: ImageView, any: Any?) {
            if(any != null)
                if(any is Int){
                    view.setImageResource(any)
                }else {
                    Glide.with(view.context)
                        .load(any)
                        .centerCrop()
                        .into(view)
                }
            else
                view.setImageResource(0)
        }
    }
}