package com.example.matictest.utils

import com.example.matictest.AppConstants.Companion.DATE_TIME_FORMAT
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


object AppUtils {

    private val suffix = arrayOf("", "k", "m", "b", "t")
    private val MAX_LENGTH = 4


    fun getDate(dateString: String): String {

        return try {
            val format1 = SimpleDateFormat(DATE_TIME_FORMAT, Locale.US)
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("MMM d, yyyy", Locale.US)
            sdf.format(date)

        } catch (ex: Exception) {
            ex.printStackTrace()
            "xx"
        }

    }

    fun formatLikesNumber(number: Long): String {
        var r = DecimalFormat("##0E0").format(number)
        r = r.replace("E[0-9]".toRegex(), suffix[Character.getNumericValue(r.get(r.length - 1)) / 3])
        while (r.length > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]".toRegex())) {
            r = r.substring(0, r.length - 2) + r.substring(r.length - 1)
        }
        return r
    }

    fun getTime(dateString: String): String {

        return try {
            val format1 = SimpleDateFormat(DATE_TIME_FORMAT, Locale.US)
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("h:mm a", Locale.US)
            sdf.format(date)

        } catch (ex: Exception) {
            ex.printStackTrace()
            "xx"
        }

    }


}
