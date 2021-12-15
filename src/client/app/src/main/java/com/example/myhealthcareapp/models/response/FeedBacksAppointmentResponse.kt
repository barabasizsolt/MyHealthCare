package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class FeedBacksAppointmentResponse (
    @SerializedName("data")
    val data: List<FeedBackAppointmentResponse>
)