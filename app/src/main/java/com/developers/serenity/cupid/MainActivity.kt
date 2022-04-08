package com.developers.serenity.cupid

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dialog: AlertDialog? = null
    private lateinit var dataAdapter: DataAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val netCon = NetListener(applicationContext)
        netCon.observe(this) { connected ->
            run {
                if (connected) {
                    prog.visibility = View.VISIBLE
                    Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                    initView()
                    initMvvm()
                } else {
                    prog.visibility = View.GONE
                    Toast.makeText(this, "No Internet Connection Detected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        dataAdapter = DataAdapter()
        recyclerView.adapter = dataAdapter
    }

    private fun initMvvm(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getLiveDataObserver().observe(this, object  : Observer<DataClass>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onChanged(t: DataClass?) {
                if(t != null){
                    dataAdapter.updateData(t.data)
                    dataAdapter.notifyDataSetChanged()
                    val animationList = intArrayOf(
                        R.anim.layout_animation_up_to_down,
                        R.anim.layout_animation_down_to_up,
                        R.anim.layout_animation_right_to_left,
                        R.anim.layout_animation_left_to_right
                    )

                    var i = 0

                    val random = Random()

                    if (i < random.nextInt() - 1) {
                        i++
                    }
                    val controller = AnimationUtils.loadLayoutAnimation(
                        applicationContext,
                        animationList[i]
                    )

                    recyclerView.layoutAnimation = controller
                    recyclerView.scheduleLayoutAnimation()
                    prog.visibility = View.GONE
                } else {
                    prog.visibility = View.GONE
                    Toast.makeText(applicationContext, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            }
        })
        mainViewModel.callApi()
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder
            .setMessage(getString(R.string.sure_to_exit))
            .setCancelable(true)
            .setPositiveButton(
                getString(R.string.no)
            ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
            .setNegativeButton(
                getString(R.string.yes_exit)
            ) { _: DialogInterface?, _: Int -> finishAffinity() }
        dialog = alertDialogBuilder.create()
        dialog!!.show()
    }
}

