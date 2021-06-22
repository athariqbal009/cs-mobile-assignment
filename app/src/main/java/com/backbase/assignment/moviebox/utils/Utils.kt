package com.backbase.assignment.moviebox.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
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
            val pattern = "MMMM dd, yyyy"
            val msg = "Date is not available"
            return try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && date.isNotEmpty()) {
                    LocalDate.parse(date).format(DateTimeFormatter.ofPattern(pattern)).toString()
                } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && date.isNotEmpty()) {
                    SimpleDateFormat(pattern).format(SimpleDateFormat("yyyy-MM-dd").parse(date))
                        .toString()
                } else {
                    msg
                }
            } catch (e: Exception) {
                msg
            }
        }

        @JvmStatic
        fun convertRating(rating: Float?): Int {
                return rating!!.times(10).roundToInt()
        }
    }
}