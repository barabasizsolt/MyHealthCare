package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class ClientAppointments (
    @SerializedName("data")
    val data : List<ClientAppointmentResponse>
    )