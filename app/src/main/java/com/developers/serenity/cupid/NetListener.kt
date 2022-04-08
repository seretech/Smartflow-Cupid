package com.developers.serenity.cupid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

class NetListener(context: Context) : LiveData<Boolean>() {

    private var connMan: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var netCall: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateCon()
        connMan.registerDefaultNetworkCallback(connManCal())
    }

    private val networkReceiver = object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            updateCon()
        }
    }

    private fun updateCon() {
        val activeNet: NetworkInfo?= connMan.activeNetworkInfo
        postValue(activeNet?.isConnected == true)
    }

    private fun connManCal(): ConnectivityManager.NetworkCallback {
        netCall = object : ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                postValue(true)
            }
        }

        return netCall
    }
}