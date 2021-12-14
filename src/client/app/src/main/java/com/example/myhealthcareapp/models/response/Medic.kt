package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class Medic (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("hire_date")
    val hireDate: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
    )