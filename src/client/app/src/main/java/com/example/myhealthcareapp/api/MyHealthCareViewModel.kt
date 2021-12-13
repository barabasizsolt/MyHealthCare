package com.example.myhealthcareapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealthcareapp.models.response.*
import kotlinx.coroutines.launch
import retrofit2.Response

class MyHealthCareViewModel(private val repository: MyHealthCareRepository) : ViewModel() {
    val hospitals: MutableLiveData<Response<HospitalResponse>> = MutableLiveData()
    val myAppointments: MutableLiveData<Response<MyAppointmentsResponse>> = MutableLiveData()
    val feedbacks: MutableLiveData<Response<FeedbackResponse>> = MutableLiveData()
    val singleAppointment: MutableLiveData<Response<SingleAppointmentResponse>> = MutableLiveData()
    val singlefeedback: MutableLiveData<Response<SingleFeedbackResponse>> = MutableLiveData()

    fun loadHospitals() {
        viewModelScope.launch {
            val response = repository.getHospitals()
            hospitals.value = response
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

    fun getSingleAppointment(id : Int) {
        viewModelScope.launch {
            val response = repository.getSingleAppointment(id)
            singleAppointment.value = response
        }
    }

    fun getSingleFeedback(id : Int) {
        viewModelScope.launch {
            val response = repository.getSingleFeedback(id)
            singlefeedback.value = response
        }
    }
}