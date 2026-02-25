
package com.aakash.batterylogger.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.aakash.batterylogger.work.WorkScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        WorkScheduler.schedule15Min(context)
    }
}
