
package com.aakash.batterylogger.util

import android.content.Context
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileWriter

object CsvLogger {
    private const val FILE_NAME = "battery_log.csv"

    fun getFile(ctx: Context): File {
        val dir = ctx.getExternalFilesDir(null) ?: ctx.filesDir
        return File(dir, FILE_NAME)
    }

    fun append(ctx: Context, timestamp: String, percent: Int) {
        val f = getFile(ctx)
        val exists = f.exists()
        FileWriter(f, true).use { w ->
            if (!exists) w.append("timestamp,battery_percent
")
            w.append("$timestamp,$percent
")
        }
    }

    fun getShareableUri(ctx: Context, file: File) =
        FileProvider.getUriForFile(ctx, "${ctx.packageName}.provider", file)
}
