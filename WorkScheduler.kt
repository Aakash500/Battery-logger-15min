
package com.aakash.batterylogger.work

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkScheduler {
    private const val UNIQUE_NAME = "battery_log_periodic"

    fun schedule15Min(ctx: Context) {
        val request = PeriodicWorkRequestBuilder<BatteryLogWorker>(15, TimeUnit.MINUTES)
            .setConstraints(Constraints.NONE)
            .build()
        WorkManager.getInstance(ctx).enqueueUniquePeriodicWork(
            UNIQUE_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }

    fun cancel(ctx: Context) {
        WorkManager.getInstance(ctx).cancelUniqueWork(UNIQUE_NAME)
    }
}
