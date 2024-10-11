package com.example.potatoservice.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://0293199f-5857-447a-b461-97d6303a1fba.mock.pstmn.io/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // APIService 인스턴스
    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

    // SignUpService 인스턴스
//    val signUpService: APIService by lazy {
//        retrofit.create(APIService::class.java)
//    }


}
