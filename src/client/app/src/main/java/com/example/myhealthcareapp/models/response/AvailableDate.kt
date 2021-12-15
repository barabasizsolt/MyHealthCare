package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class AvailableDate (
    @SerializedName("start_date")
    val startDate : String,
    @SerializedName("end_date")
    val endDate : String
    )