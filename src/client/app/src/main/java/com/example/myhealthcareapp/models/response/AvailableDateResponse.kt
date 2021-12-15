package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class AvailableDateResponse (
    @SerializedName("data")
    val data : List<AvailableDate>
    )