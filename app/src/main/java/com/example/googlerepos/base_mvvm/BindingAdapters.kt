package com.example.googlerepos.base_mvvm

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.example.googlerepos.R
import com.example.googlerepos.utils.kotlin.fadeIn
import com.example.googlerepos.utils.kotlin.fadeOut

@BindingAdapter("android:fadeVisible")
fun setFadeVisible(view: View, visible: Boolean? = false) {
    view.animate().cancel()
    if (visible == true) {
        if (view.visibility == View.GONE)
            view.fadeIn()
    } else {
        if (view.visibility == View.VISIBLE)
            view.fadeOut()
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_person_placeholder)
            error(R.drawable.ic_launcher_foreground)
        }
    }

}