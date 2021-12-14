package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.user.Client
import com.google.gson.annotations.SerializedName

data class ClientResponse (
    @SerializedName("data")
    val data : Client
    )