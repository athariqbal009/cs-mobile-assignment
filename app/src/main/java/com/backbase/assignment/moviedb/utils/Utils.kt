package com.backbase.assignment.moviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Utils {
    companion object {
        @JvmStatic
        fun isConnected(context: Context): Boolean {
            return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {
                (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.getNetworkCapabilities(it.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                } else {
                    it.activeNetworkInfo?.isConnected
                }) == true
            }
        }

        @JvmStatic
        fun convertDate(date:String): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(date, DateTimeFormatter.ofPattern("MMMM dd, yyyy")).toString()
            } else {
                SimpleDateFormat("MMMM dd, yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date)).toString()
            }
        }
    }
}