package com.alphaapps.peoplecerttask.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alphaapps.peoplecerttask.R
import com.alphaapps.peoplecerttask.databinding.LayoutFrameRecordRowBinding
import com.nagwa.files.core.domain.FrameSessionModel

/**
 * Created by Muhammad Noamany
 * Email: muhammadnoamany@gmail.com
 */
class FramesAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val frames: MutableList<FrameSessionModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val fileRowBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.layout_frame_record_row, parent, false
        )
        return FrameViewHolder(fileRowBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FrameViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): FrameSessionModel {
        return frames[position]
    }

    override fun getItemCount(): Int {
        return frames.size
    }

    fun setData(list: List<FrameSessionModel>) {
        this.frames.clear()
        this.frames.addAll(list)
        notifyDataSetChanged()
    }


    inner class FrameViewHolder(private val dataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(file: FrameSessionModel) {
            val rowBinding = dataBinding as LayoutFrameRecordRowBinding
            with(rowBinding) {
                dataModel = file
            }
        }
    }
}