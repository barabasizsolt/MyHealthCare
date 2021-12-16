package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.*
import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import com.example.myhealthcareapp.models.user.Client
import retrofit2.Response
import retrofit2.http.*

interface MyHealthCareApi {
    @GET("api/hospitals")
    suspend fun getHospitals(): Response<HospitalResponse>

    @GET("api/appointments/client/{id}")
    suspend fun getAppointments(
        @Path("id") id : Int
    ): Response<ClientAppointments>

    @GET("api/feedbacks/client/{id}")
    suspend fun getFeedbacks(
        @Path("id") id : Int
    ): Response<FeedBacksAppointmentResponse>

    @GET("api/medicaldepartments/hospital/{hospital_id}")
    suspend fun getMedicalDepartments(
        @Path("hospital_id") hospital_id : Int
    ) : Response<MedicalDepartmentResponse>

    @GET("api/medics/{medical_dep_id}")
    suspend fun getMedics(
        @Path("medical_dep_id") medical_dep_id : Int
    ) : Response<MedicsResponse>

    @POST("api/client/registerClient")
    suspend fun registerClient(
        @Body client: Client
    ) : Response<RegisterResponse>

    @GET("api/client/{email}")
    suspend fun getClient(
        @Path("email") email : String
    ) : Response<ClientResponse>

    @GET("/api/appointments/medicDates/{medic_id}")
    suspend fun getMedicDates(
        @Path("medic_id") medicId : String
    ) : Response<AvailableDateResponse>

    @POST("api/appointments/makeAppointment")
    suspend fun makeAppointment(
        @Body appointment: MakeAppointment
    ) : Response<MakeAppointmentResponse>

    @GET("/api/medics/{email}/{password}")
    suspend fun medicLogin(
        @Path("email") email: String,
        @Path("password") password: String
    ) : Response<MedicLoginResponse>

    @GET("/api/appointments/medic/{id}")
    suspend fun getMedicAppointments(
        @Path("id") id: Int
    ) : Response<ClientAppointments>
}