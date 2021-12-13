package com.example.myhealthcareapp.models.response

import com.example.myhealthcareapp.models.Feedback
import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("data")
    val data: List<Feedback>
)

data class SingleFeedbackResponse(
    @SerializedName("data")
    val data: Feedback
)

