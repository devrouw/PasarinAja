package com.bagicode.foodmarketkotlin.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileMenuAdapter(private val listData: List<ProfileMenuModel>) :
    RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : ProfileMenuModel) {
            itemView.apply {
                tvTitle.text = data.title
            }
        }
    }
}