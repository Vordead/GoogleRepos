package com.example.googlerepos.features.google_repos.api

import com.example.googlerepos.features.google_repos.model.RepositoryItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposService {
    @GET("orgs/{username}/repos")
    suspend fun fetchRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): List<RepositoryItem>
}