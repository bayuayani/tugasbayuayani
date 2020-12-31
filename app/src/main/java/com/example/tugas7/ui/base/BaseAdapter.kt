package com.example.tugas7.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tugas7.BR

abstract class BaseAdapter<T>(@field:LayoutRes private var layoutId: Int) :
    RecyclerView.Adapter<BaseAdapter<T>.Viewholder>(), BindableAdapter<List<T>> {

    private var listData: List<T>? = null

    init {
        this.setHasStableIds(true)
    }

    inner class Viewholder(itemView: View) : ViewHolder(itemView){
    private val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
        fun onBind(variabel: Int?, data: Any?) : ViewDataBinding?{
            return variabel?.takeIf { data != null }?.let {
                binding?.apply {
                    setVariable(it, data)
                    executePendingBindings()
                }
            }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent,false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        listData?.let {
            val data = it(position)
            val binding = holder.onBind(BR.model, data)
            onBind(binding, data)
            holder.itemView.setOnClickListener { onClick(binding, data) }
        }
    }

    abstract fun it(position: Int): T

    abstract fun onBind(binding: ViewDataBinding?, data: T)

    abstract fun onClick(binding: ViewDataBinding?, data: T)

    override fun getItemCount(): Int {
        return listData?.size ?: 0
    }

    override fun setData(data: List<T>?) {
        listData = data
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}


    interface BindableAdapter<T>{
    fun setData(data: T?)
    }

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> recyclerViewData(recyclerView: RecyclerView, data: T?){
    if (recyclerView.adapter is BindableAdapter<*>){
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}