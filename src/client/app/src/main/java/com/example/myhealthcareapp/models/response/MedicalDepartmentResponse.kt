package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.MedicalDepartment
import com.google.gson.annotations.SerializedName

data class MedicalDepartmentResponse (
    @SerializedName("data")
    val data : List<MedicalDepartment>
)