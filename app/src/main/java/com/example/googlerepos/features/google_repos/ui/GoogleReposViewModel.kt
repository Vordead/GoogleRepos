package com.example.googlerepos.features.google_repos.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.googlerepos.base_mvvm.BaseViewModel
import com.example.googlerepos.features.google_repos.api.RetrofitClient
import com.example.googlerepos.features.google_repos.data.repo.RepoListRepository
import com.example.googlerepos.features.google_repos.model.RepositoryItem
import kotlinx.coroutines.flow.Flow

class GoogleReposViewModel : BaseViewModel() {
    private val repository: RepoListRepository = RepoListRepository(RetrofitClient.getNetworkApi())

    private var searchResult: Flow<PagingData<RepositoryItem>>? = null
    private var currentUserName: String? = null

    fun searchRepos(username: String): Flow<PagingData<RepositoryItem>> {
        if (username == currentUserName && searchResult != null) {
            return searchResult as Flow<PagingData<RepositoryItem>>
        }
        currentUserName = username
        val newResult = repository.fetchRepos(username)
            .cachedIn(viewModelScope)
        searchResult = newResult
        return newResult
    }

    fun retrySearch() {
        if (currentUserName != null) {
            searchRepos(currentUserName!!)
        }
    }
}