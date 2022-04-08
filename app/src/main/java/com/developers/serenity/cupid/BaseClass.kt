package com.developers.serenity.cupid

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn
class BaseClass {

  //  private val reInterface : ReInterface = TODO()

  //  private val baseUrl = "https://cupidapi.smartflowtech.org/api/v1/ntwp/"

    private val reInterface : ReInterface

    companion object {
        const val baseUrl = "https://cupidapi.smartflowtech.org/api/v1/ntwp/"
    }

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        reInterface = retrofit.create(ReInterface::class.java)

    }
/*
    @Singleton
    @Provides
    fun getReInterface(retrofit: Retrofit):ReInterface{
        return retrofit.create(ReInterface::class.java)
    }


  //  @Singleton
  //  @Provides
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
*/
    fun getData() : Call<DataClass> {
        return reInterface.getData()
    }
}