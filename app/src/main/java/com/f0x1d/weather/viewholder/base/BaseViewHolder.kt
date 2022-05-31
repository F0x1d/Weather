package com.f0x1d.weather.viewholder.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T, D : ViewBinding>(protected val binding: D): RecyclerView.ViewHolder(binding.root) {
    abstract fun bindTo(data: T)
    open fun recycle() {}
}