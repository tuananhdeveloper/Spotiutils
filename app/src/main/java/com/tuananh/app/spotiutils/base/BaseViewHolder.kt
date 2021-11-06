package com.tuananh.app.spotiutils.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(
    binding: ViewBinding,
    private val onItemClickListener: (T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null

    init {
        binding.root.setOnClickListener {
            itemData?.let { data ->
                onItemClickListener(data)
            }
        }
    }

    open fun bind(item: T) {
        itemData = item
    }
}