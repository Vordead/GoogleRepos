package com.example.googlerepos.features.google_repos.empty

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {
    private val requestTimeOut:Long = 50 //in seconds

    private var client: OkHttpClient? = null
    var headers: HashMap<String, String>? = HashMap()
    private var retrofit: Retrofit? = null

    //--------------------------------------------------------------------------------------
    fun cancelAll() {
        if (client != null) {
            client!!.dispatcher.cancelAll()
        }
    }

    //--------------------------------------------------------------------------------------
    fun getClient(
        baseURL: String,
        _headers: HashMap<String, String>?,
        requestBody: RequestBody?
    ): Retrofit? {
        headers = _headers
        client = null
        val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss Z")
            .setLenient()
            .create()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .client(getHttpClient(headers, requestBody)!!)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
        return retrofit
    }
    //--------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------
    private fun getHttpClient(
        headers: HashMap<String, String>?,
        requestBody: RequestBody?
    ): OkHttpClient? {
            val headersInterceptor = Interceptor { chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                val originalHttpUrl: HttpUrl = original.url
                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .build()
                if (!headers.isNullOrEmpty()) {
                    for (entry in headers.entries) {
                        requestBuilder.header(
                            entry.key,
                            entry.value
                        ) // <-- this is the important line
                    }
                }
                if (requestBody != null) {
                    requestBuilder.post(requestBody)
                }
                val request: Request = requestBuilder.url(url).build()
                chain.proceed(request)
            }
            val interceptor = HttpLoggingInterceptor()
            val builder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(headersInterceptor)
                .addInterceptor(Interceptor { chain ->
                    val request: Request = chain.request()
                    val response: Response = chain.proceed(request)
                    val raw: String = response.body!!.string()
                    Log.d("Separator", "==================================")
                    Log.d("URL:  ", response.request.url.toString())
                    Log.d("RawHeaders:  ", response.request.headers.toString())
                    if (response.request.body != null) {
                        Log.d("body:  ", response.request.body.toString())
                    }
                    Log.d("Rawresponse:  ", raw)
                    Log.d("Separator", "==================================")
                    retrofit = null
                    val body: ResponseBody = raw.toResponseBody(response.body!!.contentType())
                    response.newBuilder()
                        .body(body)
                        .build()
                })
                .readTimeout(requestTimeOut, TimeUnit.SECONDS)
                .writeTimeout(
                    requestTimeOut,
                    TimeUnit.SECONDS
                )
                .connectTimeout(
                    requestTimeOut,
                    TimeUnit.SECONDS
                )
                .addInterceptor(interceptor)
            client = builder.build()

        return client
    }
//----------------------------------------------------------------------------------------------
}