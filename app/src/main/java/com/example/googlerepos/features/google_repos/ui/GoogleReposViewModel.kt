package com.example.googlerepos.features.google_repos.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.googlerepos.base_mvvm.BaseViewModel
import com.example.googlerepos.features.google_repos.api.RetrofitClient
import com.example.googlerepos.features.google_repos.data.repo.RepoListRepository
import com.example.googlerepos.features.google_repos.model.RepositoryItem
import kotlinx.coroutines.flow.*

class GoogleReposViewModel : BaseViewModel() {
    private val repository: RepoListRepository = RepoListRepository(RetrofitClient.getNetworkApi())

    private var searchResult: Flow<PagingData<RepositoryItem>>? = null
    private var currentUserName: String? = null

    private val _searchResultFlow = MutableStateFlow<PagingData<RepositoryItem>?>(null)
    val searchResultFlow: StateFlow<PagingData<RepositoryItem>?> = _searchResultFlow.asStateFlow()

    suspend fun searchRepos(username: String) {
        if (username == currentUserName && searchResult != null) {
            _searchResultFlow.value = searchResult?.first()
            return
        }
        currentUserName = username
        searchResult = repository.fetchRepos(username)
            .cachedIn(viewModelScope)
        _searchResultFlow.value = searchResult?.first()
    }

    suspend fun retrySearch() {
        if (currentUserName != null) {
            searchRepos(currentUserName!!)
        }
    }
}