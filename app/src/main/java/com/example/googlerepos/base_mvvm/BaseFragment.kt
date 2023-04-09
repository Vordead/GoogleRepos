package com.example.googlerepos.base_mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM : ViewModel> : Fragment() {

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        (viewModel as BaseViewModel).showToastInt.observe(this) {
            Toast.makeText(activity, getString(it), Toast.LENGTH_LONG).show()
        }
        (viewModel as BaseViewModel).showToast.observe(this) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        (viewModel as BaseViewModel).showSnackBar.observe(this) {
            val snack = Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG)
            snack.show()
        }
        (viewModel as BaseViewModel).showSnackBarInt.observe(this) {
            val snack = Snackbar.make(this.requireView(), getString(it), Snackbar.LENGTH_LONG)
            snack.show()
        }
    }
}