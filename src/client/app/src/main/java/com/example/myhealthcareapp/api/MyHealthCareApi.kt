package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.HospitalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MyHealthCareApi {
    @GET("api/hospitals")
    suspend fun getHospitals(): Response<HospitalResponse>
}