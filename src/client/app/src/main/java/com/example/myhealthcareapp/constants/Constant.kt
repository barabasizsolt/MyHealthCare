package com.example.myhealthcareapp.constants

import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object Constant {
    const val CNP_REGEX = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d\$"

    const val HospitalIdString = "HospitalIdString"
    const val HospitalId = "HospitalId"

    fun getDate(timeStamp: Long): String? {
        val formatter = SimpleDateFormat("MMM dd, yyy", Locale.getDefault())
        return formatter.format(timeStamp)
    }
}