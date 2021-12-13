package com.example.myhealthcareapp.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealthcareapp.models.response.HospitalResponse
import com.example.myhealthcareapp.models.response.MedicalDepartmentResponse
import com.example.myhealthcareapp.models.response.MedicsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MyHealthCareViewModel(private val repository: MyHealthCareRepository) : ViewModel() {
    val hospitals: MutableLiveData<Response<HospitalResponse>> = MutableLiveData()
    val departments: MutableLiveData<Response<MedicalDepartmentResponse>> = MutableLiveData()
    val medics: MutableLiveData<Response<MedicsResponse>> = MutableLiveData()

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
}