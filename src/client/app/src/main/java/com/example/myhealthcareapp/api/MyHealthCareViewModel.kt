package com.example.myhealthcareapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import com.example.myhealthcareapp.models.response.*
import com.example.myhealthcareapp.models.user.Client
import kotlinx.coroutines.launch
import retrofit2.Response

class MyHealthCareViewModel(private val repository: MyHealthCareRepository) : ViewModel() {
    val hospitals: MutableLiveData<Response<HospitalResponse>> = MutableLiveData()
    val departments: MutableLiveData<Response<MedicalDepartmentResponse>> = MutableLiveData()
    val medics: MutableLiveData<Response<MedicsResponse>> = MutableLiveData()
    val myAppointments: MutableLiveData<Response<ClientAppointments>> = MutableLiveData()
    val feedbacks: MutableLiveData<Response<FeedBacksAppointmentResponse>> = MutableLiveData()
    val registration: MutableLiveData<Response<RegisterResponse>> = MutableLiveData()
    val client: MutableLiveData<Response<ClientResponse>> = MutableLiveData()
    val medicDates: MutableLiveData<Response<AvailableDateResponse>> = MutableLiveData()
    val makeAppointment: MutableLiveData<Response<MakeAppointmentResponse>> = MutableLiveData()

    fun loadHospitals() {
        viewModelScope.launch {
            val response = repository.getHospitals()
            hospitals.value = response
        }
    }

    fun loadDepartments(hospitalId: Int){
        viewModelScope.launch {
            val response = repository.getMedicalDepartments(hospitalId)
            departments.value = response
        }
    }

    fun loadMedics(departmentId: Int){
        viewModelScope.launch {
            val response = repository.getMedics(departmentId)
            medics.value = response
        }
    }

    fun getAppointments(id : Int) {
        viewModelScope.launch {
            val response = repository.getAppointments(id)
            myAppointments.value = response
        }
    }

    fun getFeedbacks(id : Int) {
        viewModelScope.launch {
            val response = repository.getFeedbacks(id)
            feedbacks.value = response
        }
    }

    fun registerClient(registerClient: Client) {
        viewModelScope.launch {
            val response = repository.registration(registerClient)
            registration.value = response
        }
    }

    fun getClient(email: String){
        viewModelScope.launch {
            val response = repository.getClient(email)
            client.value = response
        }
    }

    fun getMedicDates(medicId: String){
        viewModelScope.launch {
            val response = repository.getMedicDates(medicId)
            medicDates.value = response
        }
    }

    fun makeAppointment(appointment: MakeAppointment){
        viewModelScope.launch {
            val response =  repository.makeAppointment(appointment)
            makeAppointment.value = response
        }
    }
}