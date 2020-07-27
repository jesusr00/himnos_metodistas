package com.devdroid.himnosmetodistas.pakagename.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devdroid.himnosmetodistas.pakagename.R
import com.devdroid.himnosmetodistas.pakagename.listeners.RecyclerviewAdapterListener
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import kotlinx.android.synthetic.main.hymn_list_template.view.*

class HymnListRecyclerviewAdapter(
    private val items: ArrayList<HymnTitle>,
    private val listener: RecyclerviewAdapterListener<HymnTitle>): RecyclerView.Adapter<HymnListRecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(listener.getContext()).inflate(R.layout.hymn_list_template, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hymn = items[position]
        holder.itemView.setOnClickListener { listener.onClick(hymn) }
        holder.itemView.hymnNum.text = hymn.num.toString()
        holder.itemView.hymnTitle.text = hymn.title
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}