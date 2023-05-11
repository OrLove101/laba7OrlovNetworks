package com.orlov.android.orlovlab7

import retrofit2.http.GET
import retrofit2.http.Url

interface DateApi {
    @GET
    suspend fun getDateInfo(@Url endpoint: String): DateInfo
}