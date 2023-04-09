package com.example.googlerepos.features.google_repos.empty

class APIUtils {
    fun getService(baseURL: String): ReposService {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "multipart/form-data"
        headers["Accept"] = "application/json"
        return ApiClient().getClient(baseURL, headers, null)!!.create(ReposService::class.java)
    }
}