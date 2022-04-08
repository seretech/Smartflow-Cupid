package com.developers.serenity.cupid

import retrofit2.Call
import retrofit2.http.GET

interface ReInterface {

    @GET("get_categories")
    fun getData() : Call<DataClass>
}