package com.arivero007.beerappandroid.utils

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.arivero007.beerappandroid.R

class LoadingDialog(activity: Activity) {

    var activity: Activity = activity
    lateinit var dialog: AlertDialog

    companion object {
        @Volatile
        private var INSTANCE: LoadingDialog? = null
        fun getInstance(activity: Activity) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoadingDialog(activity).also {
                    INSTANCE = it
                }
            }
    }

    fun startLoadingDialog(){
        if(!this::dialog.isInitialized){
            var builder = AlertDialog.Builder(activity)
            var inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.loading_dialog, null))
            builder.setCancelable(false)

            dialog = builder.create()
            dialog.show()
        }else{
            if(!dialog.isShowing){
                dialog.show()
            }
        }
    }

    fun dismissLoadingDialog(){
        if(this::dialog.isInitialized){
            if(dialog.isShowing){
                dialog.dismiss()
            }
        }
    }

}