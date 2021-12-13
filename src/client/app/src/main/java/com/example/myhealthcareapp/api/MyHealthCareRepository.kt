package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import retrofit2.Response

class MyHealthCareRepository(private val instance: MyHealthCareInstance) {
    suspend fun getHospitals() : Response<HospitalResponse> {
        return instance.api.getHospitals()
    }

    suspend fun getMedicalDepartments(hospitalId: Int) : Response<MedicalDepartmentResponse> {
        return instance.api.getMedicalDepartments(hospitalId)
    }

    suspend fun getMedics(departmentId: Int) : Response<MedicsResponse> {
        return instance.api.getMedics(departmentId)
    }
}