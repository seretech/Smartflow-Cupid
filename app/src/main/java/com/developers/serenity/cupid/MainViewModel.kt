package com.developers.serenity.cupid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

  //  @Inject
    private var ser:BaseClass = BaseClass()

    private lateinit var dataList: MutableLiveData<DataClass>

    init {
       // (application as MyApp).getReCom().inject(this)
        dataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<DataClass>{
        return dataList
    }

    fun callApi(){
        val call: Call<DataClass>? = ser.getData()
       // val allData = ser.getData()
        call?.enqueue(object : Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                dataList.postValue(null)
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if(response.isSuccessful){
                    dataList.postValue(response.body())
                } else {
                    dataList.postValue(null)
                }
            }
        })
    }

}