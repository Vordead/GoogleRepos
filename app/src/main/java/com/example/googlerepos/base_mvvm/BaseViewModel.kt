package com.example.googlerepos.base_mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val showSnackBar: SingleLiveEvent<String> = SingleLiveEvent()
    val showSnackBarInt: SingleLiveEvent<Int> = SingleLiveEvent()
    val showToastInt: SingleLiveEvent<Int> = SingleLiveEvent()
    val showToast: SingleLiveEvent<String> = SingleLiveEvent()
    val showLoadingOverlay: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showLoading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showNoData: MutableLiveData<Boolean> = MutableLiveData()
}