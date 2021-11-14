package com.example.myhealthcareapp.cache

import com.example.myhealthcareapp.models.user.Client

object Cache {
    private lateinit var client: Client

    fun setClient(currentClient: Client){
        client = currentClient
    }

    fun getClient() = client
}