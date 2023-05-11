package com.orlov.android.orlovlab7

import com.google.gson.annotations.SerializedName

data class DateInfo(
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("day_of_year")
    val dayOfYear: Int
)