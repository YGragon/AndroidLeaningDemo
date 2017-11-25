package com.dongxi.rxdemo.pinsenction

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.dongxi.rxdemo.R

/**
 * Created by macmini002 on 17/11/25.
 */
class SectionAdapter (val items : List<CityBean>) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    private var context: Context? = null
    private var list:ArrayList<CityBean>? = null

    init {
        this.context = context
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item, parent,
                false) as TextView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].toString()
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}