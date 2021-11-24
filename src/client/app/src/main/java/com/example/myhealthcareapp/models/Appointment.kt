package com.example.myhealthcareapp.models

data class Appointment (
    val appointmentId : Int,
    val clientId : String,
    val hospitalId : Int,
    val medicalDepartmentId : Int,
    val medicId : Int,
    val scheduleStartDate : String,
    val scheduleEndDate : String,
    val notes : String
)