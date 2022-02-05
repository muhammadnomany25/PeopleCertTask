package com.alphaapps.peoplecerttask.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alphaapps.peoplecerttask.R
import com.alphaapps.peoplecerttask.databinding.LayoutStreamRecordRowBinding
import com.nagwa.files.core.domain.StreamSessionModel

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class StreamsAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val streams: MutableList<StreamSessionModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val fileRowBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.layout_stream_record_row, parent, false
        )
        return StreamViewHolder(fileRowBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StreamViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): StreamSessionModel {
        return streams[position]
    }

    override fun getItemCount(): Int {
        return streams.size
    }

    fun setData(list: List<StreamSessionModel>) {
        this.streams.clear()
        this.streams.addAll(list)
        notifyDataSetChanged()
    }


    inner class StreamViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(file: StreamSessionModel) {
            val rowBinding = dataBinding as LayoutStreamRecordRowBinding
            with(rowBinding) {
                dataModel = file
            }
        }
    }
}