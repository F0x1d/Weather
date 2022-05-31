package com.f0x1d.weather.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.f0x1d.weather.viewholder.base.BaseViewHolder

abstract class BaseAdapter<T, D : ViewBinding>: RecyclerView.Adapter<BaseViewHolder<T, D>>() {
    var elements = emptyList<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<T, D>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = createViewHolder(
        LayoutInflater.from(parent.context),
        parent
    )

    override fun onBindViewHolder(holder: BaseViewHolder<T, D>, position: Int) = holder.bindTo(elements[position])

    override fun getItemCount() = elements.size

    override fun onViewRecycled(holder: BaseViewHolder<T, D>) {
        super.onViewRecycled(holder)
        holder.recycle()
    }
}