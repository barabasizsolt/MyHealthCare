package com.example.myhealthcareapp.models

data class MedicalDepartment(
    val medicalDepartmentId: Int,
    val medicalDepartmentName: String,
    val medicalDepartmentPhoneNumber: String,
    val medicalDepartmentAddress: String,
    val description: String,
    val hospitalId: Int?
)