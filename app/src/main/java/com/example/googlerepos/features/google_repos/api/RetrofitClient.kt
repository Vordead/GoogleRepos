package com.example.googlerepos.features.google_repos.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


object RetrofitClient {

    private const val BASE_URL="https://api.github.com/"

    fun getNetworkApi(): ReposService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ReposService::class.java)
    }
}