package com.example.myhealthcareapp

import com.example.myhealthcareapp.api.MyHealthCareInstance
import com.example.myhealthcareapp.api.MyHealthCareRepository
import com.example.myhealthcareapp.api.MyHealthCareViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myHealthCareModule = module {
    single { MyHealthCareInstance() }
    single { MyHealthCareRepository(get()) }
    viewModel { MyHealthCareViewModel(get()) }
}