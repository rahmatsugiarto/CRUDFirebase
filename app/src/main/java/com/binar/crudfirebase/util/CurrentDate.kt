package com.binar.crudfirebase.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object CurrentDate {
    @SuppressLint("SimpleDateFormat")
    fun getCurreentDateTimeString(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(date)
    }
}