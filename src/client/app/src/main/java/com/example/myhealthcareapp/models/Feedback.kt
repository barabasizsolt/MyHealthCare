package com.example.myhealthcareapp.models

import com.google.gson.annotations.SerializedName

data class Feedback (
    @SerializedName("id")
    val feedbackId : Int,

    val appointment : Appointment,

    @SerializedName("message")
    val feedbackMessage : String,
    val billing : Int
)
