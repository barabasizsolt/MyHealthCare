package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class MakeAppointment (
    @SerializedName("client_id")
    val clientId : String,
    @SerializedName("hospital_id")
    val hospitalId : String,
    @SerializedName("medical_department_id")
    val medicalDepartmentId : String,
    @SerializedName("medic_id")
    val medicId : String,
    @SerializedName("scheduled_start_date")
    val scheduleStartDate : String,
    @SerializedName("scheduled_end_date")
    val scheduleEndDate : String,
    @SerializedName("notes")
    val notes : String
)
