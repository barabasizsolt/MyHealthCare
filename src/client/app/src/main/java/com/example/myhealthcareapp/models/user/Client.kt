package com.example.myhealthcareapp.models.user

import com.google.gson.annotations.SerializedName

data class Client (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val personalCode: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)