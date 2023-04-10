package com.example.googlerepos.features.google_repos.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.googlerepos.features.google_repos.api.ReposService
import com.example.googlerepos.features.google_repos.data.GithubPagingSource

class RepoListRepository(private val api: ReposService) {
    fun fetchRepos(userName: String) = Pager(
        pagingSourceFactory = { GithubPagingSource(api, userName) },
        config = PagingConfig(pageSize = 20)
    ).flow
}