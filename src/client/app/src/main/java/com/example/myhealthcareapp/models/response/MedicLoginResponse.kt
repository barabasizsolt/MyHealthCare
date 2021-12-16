package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class MedicLoginResponse (
    @SerializedName("data")
    val data: Medic
    )