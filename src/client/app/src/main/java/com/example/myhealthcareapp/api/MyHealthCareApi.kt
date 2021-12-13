package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.*
import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyHealthCareApi {
    @GET("api/hospitals")
    suspend fun getHospitals(): Response<HospitalResponse>

    @GET("api/appointments/client/{id}")
    suspend fun getAppointments(
        @Path("id") id : Int
    ): Response<MyAppointmentsResponse>

    @GET("api/feedbacks/client/{id}")
    suspend fun getFeedbacks(
        @Path("id") id : Int
    ): Response<FeedbackResponse>

    @GET("api/appointments/{id}")
    suspend fun getSingleAppointment(
        @Path("id") id : Int
    ): Response<SingleAppointmentResponse>

    @GET("api/feedbacks/{id}")
    suspend fun getSingleFeedback(
        @Path("id") id : Int
    ): Response<SingleFeedbackResponse>
    suspend fun getHospitals() : Response<HospitalResponse>

    @GET("api/medicaldepartments/hospital/{hospital_id}")
    suspend fun getMedicalDepartments(
        @Path("hospital_id") hospital_id : Int
    ) : Response<MedicalDepartmentResponse>

    @GET("api/medics/{medical_dep_id}")
    suspend fun getMedics(
        @Path("medical_dep_id") medical_dep_id : Int
    ) : Response<MedicsResponse>
}