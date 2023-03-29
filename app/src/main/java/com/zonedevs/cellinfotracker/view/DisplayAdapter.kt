package com.zonedevs.cellinfotracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zonedevs.cellinfotracker.models.DisplayModel

class DisplayAdapter(
    private var datalist:ArrayList<DisplayModel> = arrayListOf()
): RecyclerView.Adapter<DisplayAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvHeader: TextView = itemView.findViewById(R.id.title)
        val tvValue: TextView = itemView.findViewById(R.id.detail)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: ArrayList<DisplayModel>){
        datalist.clear()
        datalist.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = datalist[position]


        // sets the text to the textview from our itemHolder class
        holder.tvHeader.text = itemsViewModel.parameter
        holder.tvValue.text = itemsViewModel.value
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}