package com.example.myhealthcareapp.models

data class Feedback (
    val feedbackId : Int,
    val appointmentId : Int,
    val feedbackMessage : String,
    val billing : String
)