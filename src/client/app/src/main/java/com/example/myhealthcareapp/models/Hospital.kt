package com.example.myhealthcareapp.models

data class Hospital (
    val hospitalId : Int,
    val hospitalName : String,
    val hospitalPhoneNumber : String,
    val hospitalAddress : String,
    val description : String,
    val longitudeCoordinate : String,
    val latitudeCoordinate : String
)