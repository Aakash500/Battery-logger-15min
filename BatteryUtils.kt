
package com.aakash.batterylogger.util

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import java.text.SimpleDateFormat
import java.util.*

object BatteryUtils {
    fun readBatteryPercentage(ctx: Context): Int {
        val bm = ctx.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val prop = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        if (prop in 1..100) return prop
        val ifilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batt: Intent? = ctx.registerReceiver(null, ifilter)
        val level = batt?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batt?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        return if (level >= 0 && scale > 0) ((level * 100f) / scale).toInt() else -1
    }

    fun timestamp(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())
}
