package com.example.potatoservice.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://e941a67f-eab3-400d-84ab-4dd8bc0bbebb.mock.pstmn.io/"

    // Retrofit 인스턴스 생성
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
