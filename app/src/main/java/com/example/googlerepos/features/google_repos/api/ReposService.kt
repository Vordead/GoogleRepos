package com.example.googlerepos.features.google_repos.api

import com.example.googlerepos.features.google_repos.model.RepositoryItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://api.github.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



interface ReposService {
    @GET("/orgs/google/repos")
    suspend fun getRepositories(): List<RepositoryItem>
}

object GoogleReposApi {
    val retrofitService : ReposService by lazy {
        retrofit.create(ReposService::class.java) }
}