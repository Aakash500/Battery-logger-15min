
package com.aakash.batterylogger.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aakash.batterylogger.databinding.ActivityMainBinding
import com.aakash.batterylogger.util.CsvLogger
import com.aakash.batterylogger.work.WorkScheduler

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            WorkScheduler.schedule15Min(this)
            Toast.makeText(this, "Battery logging scheduled (every 15 minutes)", Toast.LENGTH_SHORT).show()
        }

        binding.btnStop.setOnClickListener {
            WorkScheduler.cancel(this)
            Toast.makeText(this, "Battery logging stopped", Toast.LENGTH_SHORT).show()
        }

        binding.btnShowPath.setOnClickListener {
            val file = CsvLogger.getFile(this)
            Toast.makeText(this, "CSV: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        }

        binding.btnShare.setOnClickListener {
            val file = CsvLogger.getFile(this)
            val uri = CsvLogger.getShareableUri(this, file)
            val share = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_PERMISSION)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(share, "Share battery_log.csv"))
        }
    }
}
