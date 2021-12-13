package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.Medic
import com.google.gson.annotations.SerializedName

data class MedicsResponse (
    @SerializedName("data")
    val data : List<Medic>
)