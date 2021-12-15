package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class MakeAppointmentResponse (
    @SerializedName("Result")
    val result: String
    )