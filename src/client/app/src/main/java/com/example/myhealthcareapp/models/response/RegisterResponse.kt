package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse (
    @SerializedName("Result")
    val result: String
    )