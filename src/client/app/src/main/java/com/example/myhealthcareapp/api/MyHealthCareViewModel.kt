package com.example.myhealthcareapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealthcareapp.models.response.HospitalResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MyHealthCareViewModel(private val repository: MyHealthCareRepository) : ViewModel() {
    val hospitals: MutableLiveData<Response<HospitalResponse>> = MutableLiveData()

    fun loadHospitals() {
        viewModelScope.launch {
            val response = repository.getHospitals()
            hospitals.value = response
        }
    }
}