package com.pagingviewmodel.data.remote

import com.google.gson.GsonBuilder
import com.pagingviewmodel.utills.Config
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kamesh singh.
 */

class ServiceGenerator {

    private var retrofit: Retrofit
    private val client: OkHttpClient
     val clientService: ApiClient

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpBuilder = OkHttpClient.Builder()

        //-----set timeout for 1 min-----------
        okHttpBuilder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
            .writeTimeout(60 * 1000, TimeUnit.MILLISECONDS)

        okHttpBuilder.addInterceptor { chain ->
            val original = chain.request()
            var request: Request? = null

            //---------set header-------------
                request = original.newBuilder()
                    .header("User-Agent", "Demo")
                    .header("Accept", "application/Demo.v1.full+json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build()

            chain.proceed(request)
        }

        client = okHttpBuilder.build()
        retrofit = Retrofit.Builder().baseUrl(Config.BASE_URL).client(client)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation().create()
                )
            ).build()

        clientService = retrofit.create(ApiClient::class.java)
    }

    companion object {
        //-------create instance of the class---
        var instance: ServiceGenerator? = null
            get() {
                if (field == null) {
                    field = ServiceGenerator()
                }
                return field
            }
            private set
    }
}