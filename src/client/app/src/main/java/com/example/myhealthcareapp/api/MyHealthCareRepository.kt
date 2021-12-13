package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.HospitalResponse
import retrofit2.Response

class MyHealthCareRepository(private val instance: MyHealthCareInstance) {
    suspend fun getHospitals() : Response<HospitalResponse> {
        return instance.api.getHospitals()
    }
}