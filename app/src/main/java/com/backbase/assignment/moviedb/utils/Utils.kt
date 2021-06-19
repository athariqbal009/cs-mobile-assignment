package com.backbase.assignment.moviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

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
    }
}