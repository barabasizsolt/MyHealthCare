package com.example.myhealthcareapp.api

import com.example.myhealthcareapp.models.response.*
import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import com.example.myhealthcareapp.models.user.Client
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

    suspend fun getAppointments(id : Int) : Response<MyAppointmentsResponse> {
        return instance.api.getAppointments(id)
    }

    suspend fun getFeedbacks(id : Int) : Response<FeedbackResponse> {
        return instance.api.getFeedbacks(id)
    }

    suspend fun getSingleAppointment(id : Int) : Response<SingleAppointmentResponse> {
        return instance.api.getSingleAppointment(id)
    }

    suspend fun getSingleFeedback(id : Int) : Response<SingleFeedbackResponse> {
        return instance.api.getSingleFeedback(id)
    }

    suspend fun registration(registerClient: Client) : Response<RegisterResponse> {
        return instance.api.registerClient(registerClient)
    }

    suspend fun getClient(email: String) : Response<ClientResponse> {
        return instance.api.getClient(email)
    }
}