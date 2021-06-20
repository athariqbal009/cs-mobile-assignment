package com.backbase.assignment.moviebox.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class Utils {
    companion object {
        @JvmStatic
        fun isConnected(context: Context): Boolean {
            return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let {
                (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    it.getNetworkCapabilities(it.activeNetwork)
                        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                } else {
                    it.activeNetworkInfo?.isConnected
                }) == true
            }
        }

        @JvmStatic
        fun convertDate(date: String): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")).toString()
            } else {
                SimpleDateFormat("MMMM dd, yyyy").format(SimpleDateFormat("yyyy-MM-dd").parse(date))
                    .toString()
            }
        }

        fun convertRating(rating: Float?): Int {
                return rating!!.times(10).roundToInt()
        }
    }
}