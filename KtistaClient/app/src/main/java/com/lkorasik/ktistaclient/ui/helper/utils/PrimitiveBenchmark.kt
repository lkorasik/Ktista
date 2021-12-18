package com.lkorasik.ktistaclient.ui.helper.utils

import android.util.Log
import java.util.*

object PrimitiveBenchmark{
    private var time: Long = 0
    var startMessage = "Start"
    var endMessage = "End"

    fun start(){
        Log.i(this::class.java.canonicalName, startMessage)
        time = Calendar.getInstance().timeInMillis
    }

    fun stop() {
        val endTime = Calendar.getInstance().timeInMillis
        val result = endTime - time

        Log.i(this::class.java.canonicalName, endMessage)
        Log.i(this::class.java.canonicalName, "Result: $result")
    }
}