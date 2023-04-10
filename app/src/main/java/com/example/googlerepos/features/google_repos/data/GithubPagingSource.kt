package com.example.googlerepos.features.google_repos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.googlerepos.features.google_repos.api.ReposService
import com.example.googlerepos.features.google_repos.model.RepositoryItem

private const val FIRST_PAGE = 1;

class GithubPagingSource(
    private val service: ReposService,
    private val userName: String
) : PagingSource<Int, RepositoryItem>() {

    override fun getRefreshKey(state: PagingState<Int, RepositoryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryItem> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val response = service.fetchRepos(userName, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}