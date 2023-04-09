package com.example.googlerepos.features.google_repos.empty

import com.example.googlerepos.features.google_repos.model.RepositoryItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ReposService {
    @GET("/orgs/google/repos")
    fun getRepositories1(): Observable <List<RepositoryItem>>
}