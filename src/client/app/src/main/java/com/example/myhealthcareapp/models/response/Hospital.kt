package com.example.myhealthcareapp.models.response

import com.google.gson.annotations.SerializedName

data class Hospital (
    @SerializedName("id")
    val hospitalId : Int,
    @SerializedName("name")
    val hospitalName : String,
    @SerializedName("contact")
    val hospitalPhoneNumber : String,
    @SerializedName("address")
    val hospitalAddress : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("longitude_coordinate")
    val longitudeCoordinate : String,
    @SerializedName("latitude_coordinate")
    val latitudeCoordinate : String
)