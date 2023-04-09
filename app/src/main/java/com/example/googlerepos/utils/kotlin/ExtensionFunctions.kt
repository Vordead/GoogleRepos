package com.example.googlerepos.utils.kotlin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.googlerepos.R

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.alpha = 0f
    this.animate().alpha(1f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeIn.alpha = 1f
        }
    })
}

fun View.fadeOut() {
    this.animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            this@fadeOut.alpha = 1f
            this@fadeOut.visibility = View.GONE
        }
    })
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun Fragment.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    arguments: Bundle? = null,
    addToBackStack: Boolean = true,
    animate: Boolean = true
) {
    this.parentFragmentManager.beginTransaction()
        .apply {
            if (animate) {
                setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right,
                    R.anim.back_slide_in_left,
                    R.anim.back_slide_out_right
                )
            }
            arguments?.let {
                fragment.arguments = it
            }
            setReorderingAllowed(true)
            add(containerViewId, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
        .commit()
}

fun Fragment.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    arguments: Bundle? = null,
    addToBackStack: Boolean = true,
    animate: Boolean = true
) {
    this.parentFragmentManager.beginTransaction()
        .apply {
            if (animate) {
                setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right,
                    R.anim.back_slide_in_left,
                    R.anim.back_slide_out_right
                )
            }
            arguments?.let {
                fragment.arguments = it
            }
            setReorderingAllowed(true)
            replace(containerViewId, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
        .commit()
}

fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    arguments: Bundle? = null,
    addToBackStack: Boolean = true,
    animate: Boolean = true
) {
    this.beginTransaction()
        .apply {
            if (animate) {
                setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right,
                    R.anim.back_slide_in_left,
                    R.anim.back_slide_out_right
                )
            }
            arguments?.let {
                fragment.arguments = it
            }
            setReorderingAllowed(true)
            add(containerViewId, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
        .commit()
}

fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    arguments: Bundle? = null,
    addToBackStack: Boolean = true,
    animate: Boolean = true
) {
    this.beginTransaction()
        .apply {
            if (animate) {
                setCustomAnimations(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right,
                    R.anim.back_slide_in_left,
                    R.anim.back_slide_out_right
                )
            }
            arguments?.let {
                fragment.arguments = it
            }
            setReorderingAllowed(true)
            replace(containerViewId, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
        .commit()
}