package com.tuananh.app.spotiutils.base

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH: BaseViewHolder<T>>(
    private val items: MutableList<T>
): RecyclerView.Adapter<VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newData: MutableList<T>) {
        items.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }
}