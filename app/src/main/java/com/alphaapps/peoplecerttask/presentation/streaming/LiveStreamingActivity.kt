package com.alphaapps.peoplecerttask.presentation.streaming

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.alphaapps.peoplecerttask.R
import com.alphaapps.peoplecerttask.constants.Constants.AGORA_APP_ID
import com.alphaapps.peoplecerttask.constants.Constants.AGORA_CHANNEL
import com.alphaapps.peoplecerttask.constants.Constants.AGORA_TOKEN
import com.alphaapps.peoplecerttask.databinding.ActivityLiveStreamBinding
import com.alphaapps.peoplecerttask.presentation.base.BaseVmActivity
import com.alphaapps.peoplecerttask.presentation.base.Status
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

/**
 * Creates Live Streaming session based on camera feed using Agora Service ("https://docs.agora.io/")
 *
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class LiveStreamingActivity : BaseVmActivity<ActivityLiveStreamBinding, LiveStreamingVM>() {

    private var mRtcEngine: RtcEngine? = null
    private var sentBytes: Long = 0L

    /**
     * Listen to sent / receive bytes
     */
    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        override fun onRtcStats(stats: RtcStats) {
            super.onRtcStats(stats)
            sentBytes = stats.txBytes.toLong()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handlePermissions()
        observeData()
        setListeners()
    }

    /**
     * Set listeners to view
     */
    private fun setListeners() {
        binding!!.flip.setOnClickListener { v -> mRtcEngine!!.switchCamera() }
    }

    /**
     * Observe data change
     */
    private fun observeData() {
        viewModel!!.streamUiModel.status.observe(this, Observer { status: Int? ->
            if (status != null && status == Status.SUCCESS.value) {
                binding!!.progressBar.visibility = View.GONE
                finish()
            } else if (status != null && status == Status.LOADING.value) {
                binding!!.progressBar.visibility = View.VISIBLE
            }
        })
    }

    /**
     * Check if needed are granted so show the camera or not so ask for the permissions
     */
    private fun handlePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
                ), 121
            )
        } else {
            initializeAndJoinChannel()
        }
    }


    /**
     * Initialize the WebRTC engine of Agora
     */
    private fun initializeAndJoinChannel() {
        mRtcEngine = try {
            RtcEngine.create(baseContext, AGORA_APP_ID, mRtcEventHandler)
        } catch (e: Exception) {
            throw RuntimeException("Check the error.")
        }
        mRtcEngine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine!!.setClientRole(Constants.CLIENT_ROLE_BROADCASTER)
        mRtcEngine!!.enableVideo()
        val container = findViewById<FrameLayout>(R.id.video_view_container)
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        container.addView(surfaceView)
        mRtcEngine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
        mRtcEngine!!.joinChannel(AGORA_TOKEN, AGORA_CHANNEL, "", 0)
    }

    /**
     * Destroy the Agora WebRTC on activity destroy
     */
    override fun onDestroy() {
        super.onDestroy()
        mRtcEngine!!.leaveChannel()
        RtcEngine.destroy()
    }

    /**
     * Check the requested permissions result
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            initializeAndJoinChannel()
        } else {
            Toast.makeText(this, "You must accept permissions first", Toast.LENGTH_LONG).show()
            onBackPressed()
        }
    }

    /**
     * Save the sent frames session to the database
     */
    override fun onBackPressed() {
        if (viewModel != null && sentBytes > 0L) {
            viewModel!!.insertFrameSession(sentBytes)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_live_stream
    }

    override fun getVmClass(): Class<*> {
        return LiveStreamingVM::class.java
    }
}