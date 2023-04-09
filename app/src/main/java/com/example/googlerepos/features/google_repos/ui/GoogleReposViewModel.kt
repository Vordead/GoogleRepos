package com.example.googlerepos.features.google_repos.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.googlerepos.base_mvvm.BaseViewModel
import com.example.googlerepos.features.google_repos.model.RepositoryItem
import com.example.googlerepos.features.google_repos.empty.APIUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GoogleReposViewModel : BaseViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

/*    val resultsList = MutableStateFlow<ArrayList<RepositoryItem>>(arrayListOf())

    val resultsListObserver: MutableStateFlow<ArrayList<RepositoryItem>>
        get() = resultsList*/

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
/*
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
*/

    fun fetchRepos() {
        val observable = APIUtils().getService("https://api.github.com").getRepositories1()
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<RepositoryItem>> {
                override fun onSubscribe(d: Disposable) {
                    // You can use this to handle the disposable
                }

                override fun onNext(t: List<RepositoryItem>) {
                    _repos.value=t
                }


                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                    // You can use this to handle the completion event
                }
            })
        _status.value = "Success"
        showSnackBar.value = "Success"

    }

}