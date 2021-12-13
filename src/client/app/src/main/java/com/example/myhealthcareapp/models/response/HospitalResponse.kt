package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.Hospital
import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @SerializedName("data")
    val data: List<Hospital>
    )