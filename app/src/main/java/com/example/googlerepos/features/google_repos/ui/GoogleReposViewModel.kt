package com.example.googlerepos.features.google_repos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.googlerepos.features.google_repos.api.GoogleReposApi
import com.example.googlerepos.base_mvvm.BaseViewModel
import com.example.googlerepos.features.google_repos.model.RepositoryItem
import kotlinx.coroutines.launch

class GoogleReposViewModel : BaseViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status


    private val _repos = MutableLiveData<List<RepositoryItem>>()
    val repos : LiveData<List<RepositoryItem>> = _repos

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
//    init {
//        getMarsPhotos()
//    }

    /**
     * Gets Repos information from the API Retrofit service and updates
     */
     fun fetchRepos() {
        viewModelScope.launch {
            showLoading.value = true
            try {
                val listResult = GoogleReposApi.retrofitService.getRepositories()
                println(listResult)
                _repos.value = GoogleReposApi.retrofitService.getRepositories()

//                Log.d(GoogleReposFragment::javaClass.toString(), "getMarsPhotos: $listResult")
                _status.value = "Success: ${listResult.size} Google repositories retrieved"
                showSnackBar.value = "Success: ${listResult.size} Google repositories retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
        showLoading.postValue(false)
    }

}