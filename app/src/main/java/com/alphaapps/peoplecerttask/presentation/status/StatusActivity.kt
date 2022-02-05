package com.alphaapps.peoplecerttask.presentation.status

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphaapps.peoplecerttask.R
import com.alphaapps.peoplecerttask.databinding.ActivityStatusBinding
import com.alphaapps.peoplecerttask.presentation.adapters.FramesAdapter
import com.alphaapps.peoplecerttask.presentation.adapters.StreamsAdapter
import com.alphaapps.peoplecerttask.presentation.base.BaseVmActivity
import com.alphaapps.peoplecerttask.presentation.base.Status
import com.alphaapps.peoplecerttask.utils.SizeUtils

class StatusActivity : BaseVmActivity<ActivityStatusBinding, StatusDataVM>() {
    private lateinit var framesAdapter: FramesAdapter
    private lateinit var streamsAdapter: StreamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecyclers()
        observeData()
        setListeners()
    }

    /**
     * Set listeners for view
     */
    private fun setListeners() {
        binding!!.typesRG.setOnCheckedChangeListener { radioGroup, id ->
            when (id) {
                R.id.imageFramesRB -> {
                    resetUi()
                    viewModel!!.loadData(false)
                }
                R.id.videoStreamsRB -> {
                    viewModel!!.loadData(true)
                    resetUi()
                }
            }
        }
        binding!!.imageFramesRB.isChecked = true
    }

    /**
     * Reset Ui Recyclers and textViews
     */
    private fun resetUi() {
        framesAdapter.setData(ArrayList())
        streamsAdapter.setData(ArrayList())
        binding!!.totalRecordTV.text = "Total record: "
    }

    /**
     * Set Recycler view and attach it to the adapter
     */
    private fun setRecyclers() {
        // The frames recycler and its adapter initialization
        framesAdapter = FramesAdapter(this)
        binding!!.framesRecycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding!!.framesRecycler.adapter = framesAdapter

        // The streams recycler and its adapter initialization
        streamsAdapter = StreamsAdapter(this)
        binding!!.streamsRecycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding!!.streamsRecycler.adapter = streamsAdapter
    }

    /**
     * Observe view model data
     */
    private fun observeData() {
        viewModel!!.framesUiModel.status.observe(this, Observer {
            handleResult(it)
        })
        viewModel!!.streamUiModel.status.observe(this, Observer {
            handleResult(it)
        })
    }

    /**
     * Handle the result of viewModel status
     */
    private fun handleResult(it: Int?) {
        when (it) {
            Status.SUCCESS.value -> {
                binding!!.progressBar.visibility = View.GONE
                setUiData()
            }
            Status.ERROR.value -> binding!!.progressBar.visibility = View.GONE
            else -> binding!!.progressBar.visibility = View.VISIBLE
        }
    }

    /**
     * Set Ui Data
     */
    private fun setUiData() {
        if (binding!!.imageFramesRB.isChecked) {
            setFramesData()
        } else if (binding!!.videoStreamsRB.isChecked) {
            setStreamsData()
        }

    }

    private fun setStreamsData() {
        streamsAdapter.setData(viewModel!!.streamUiModel.filesData.value!!)
        binding!!.totalRecordTV.text =
            "Total record: ${SizeUtils.formatSize(viewModel!!.streamUiModel.filesData.value!!.sumOf { it.sentBytes })}"
    }

    private fun setFramesData() {
        framesAdapter.setData(viewModel!!.framesUiModel.filesData.value!!)
        binding!!.totalRecordTV.text =
            "Total record: ${viewModel!!.framesUiModel.filesData.value!!.sumBy { it.framesSent }} frame"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_status
    }

    override fun getVmClass(): Class<*> {
        return StatusDataVM::class.java
    }
}