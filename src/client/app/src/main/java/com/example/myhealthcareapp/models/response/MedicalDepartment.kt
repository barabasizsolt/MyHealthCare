package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class MedicalDepartment(
    @SerializedName("id")
    val medicalDepartmentId: Int,
    @SerializedName("name")
    val medicalDepartmentName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("contact")
    val medicalDepartmentPhoneNumber: String,
    @SerializedName("hospital")
    val hospitalName: String
)