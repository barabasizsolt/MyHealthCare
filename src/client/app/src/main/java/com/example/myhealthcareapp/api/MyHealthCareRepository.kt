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

    suspend fun getAppointments(id : Int) : Response<ClientAppointments> {
        return instance.api.getAppointments(id)
    }

    suspend fun getFeedbacks(id : Int) : Response<FeedBacksAppointmentResponse> {
        return instance.api.getFeedbacks(id)
    }

    suspend fun registration(registerClient: Client) : Response<RegisterResponse> {
        return instance.api.registerClient(registerClient)
    }

    suspend fun getClient(email: String) : Response<ClientResponse> {
        return instance.api.getClient(email)
    }

    suspend fun getMedicDates(medicId: String) : Response<AvailableDateResponse> {
        return instance.api.getMedicDates(medicId)
    }

    suspend fun makeAppointment(appointment: MakeAppointment) : Response<MakeAppointmentResponse> {
        return instance.api.makeAppointment(appointment)
    }

    suspend fun medicLogin(email: String, password: String) : Response<MedicLoginResponse> {
        return instance.api.medicLogin(email, password)
    }

    suspend fun getMedicAppointment(id: Int) : Response<ClientAppointments> {
        return instance.api.getMedicAppointments(id)
    }
}