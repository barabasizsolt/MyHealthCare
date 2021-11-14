package com.example.myhealthcareapp.models.user

data class Client (
    val clientId: String,
    val clientFirstName: String,
    val clientLastName: String,
    val clientEmail: String,
    val clientPersonalCode: String
    )