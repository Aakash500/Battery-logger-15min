
package com.aakash.batterylogger.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aakash.batterylogger.util.BatteryUtils
import com.aakash.batterylogger.util.CsvLogger

class BatteryLogWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val pct = BatteryUtils.readBatteryPercentage(applicationContext)
        CsvLogger.append(applicationContext, BatteryUtils.timestamp(), pct)
        return Result.success()
    }
}
