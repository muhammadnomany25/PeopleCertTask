package com.alphaapps.peoplecerttask.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alphaapps.peoplecerttask.R
import com.alphaapps.peoplecerttask.databinding.ActivityMainBinding
import com.alphaapps.peoplecerttask.presentation.live_frames.LiveCameraFramesActivity
import com.alphaapps.peoplecerttask.presentation.status.StatusActivity
import com.alphaapps.peoplecerttask.presentation.streaming.LiveStreamingActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setListeners()
    }

    /**
     * Set listeners to views
     */
    private fun setListeners() {
        binding.liveFramesBT.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LiveCameraFramesActivity::class.java
                )
            )
        }
        binding.liveStreamBT.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LiveStreamingActivity::class.java
                )
            )
        }
        binding.statusBT.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    StatusActivity::class.java
                )
            )
        }

    }
}