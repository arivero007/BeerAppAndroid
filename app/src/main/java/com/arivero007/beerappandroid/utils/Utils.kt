package com.arivero007.beerappandroid.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Utils {

    companion object{

        fun showAlert(context: Context, text: String){
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setMessage(text)
            alertDialogBuilder.setPositiveButton("Close"){dialog, which ->
                // Do something when user press the positive button
            }
            alertDialogBuilder.show()
        }
    }
}