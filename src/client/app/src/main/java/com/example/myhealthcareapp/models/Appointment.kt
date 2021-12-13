package com.example.myhealthcareapp.models

import com.google.gson.annotations.SerializedName

data class Appointment (
    @SerializedName("id")
    val appointmentId : Int,

    @SerializedName("client_name")
    val clientName : String,

    @SerializedName("hospital_name")
    val hospitalName : String,

    @SerializedName("medical_department_name")
    val medicalDepartmentName : String,

    @SerializedName("medic_name")
    val medicName : String,

    @SerializedName("scheduled_start_date")
    val scheduleStartDate : String,

    @SerializedName("scheduled_end_date")
    val scheduleEndDate : String,
    val notes : String
)
