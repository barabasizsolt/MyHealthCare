package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class MyAppointmentsResponse(
    @SerializedName("data")
    val data: List<Appointment>
)

data class SingleAppointmentResponse(
    @SerializedName("data")
    val data: Appointment
)
