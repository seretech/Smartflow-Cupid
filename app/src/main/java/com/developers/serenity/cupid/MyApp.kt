package com.developers.serenity.cupid

import android.app.Application
import dagger.Module

class MyApp: Application() {

    private lateinit var reCom: ReCom

    override fun onCreate() {
        super.onCreate()
/*
        reCom = DaggerRetroComponent.builder()
            .retroModule(BaseClass())
            .build()
        */
    }

  //  fun getReCom(): ReCom{
      //  return reCom
    //}
}