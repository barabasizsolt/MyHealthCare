package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.response.Appointment
import com.google.gson.annotations.SerializedName

data class Feedback (
    @SerializedName("id")
    val feedbackId : Int,
    @SerializedName("appointment")
    val appointment : Appointment,
    @SerializedName("message")
    val feedbackMessage : String,
    @SerializedName("billing")
    val billing : Int
)
