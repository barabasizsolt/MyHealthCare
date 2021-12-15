package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class FeedBackAppointmentResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("appointment")
    val appointment: ClientAppointmentResponse,
    @SerializedName("message")
    val message: String,
    @SerializedName("billing")
    val billing: Int
    )