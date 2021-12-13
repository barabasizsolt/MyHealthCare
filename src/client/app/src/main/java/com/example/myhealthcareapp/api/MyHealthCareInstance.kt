package com.example.myhealthcareapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyHealthCareInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api : MyHealthCareApi by lazy {
        retrofit.create(MyHealthCareApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://myhealthcare-app.herokuapp.com/"
    }
}