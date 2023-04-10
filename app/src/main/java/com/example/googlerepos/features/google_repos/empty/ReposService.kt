package com.example.googlerepos.features.google_repos.empty

import com.example.googlerepos.features.google_repos.model.RepositoryItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposService {
    @GET("/orgs/google/repos")
     fun getRepositories(
        @Query("page") page: Int?=null,
        @Query("per_page") size: Int?=null
     ): Observable <List<RepositoryItem>>
}