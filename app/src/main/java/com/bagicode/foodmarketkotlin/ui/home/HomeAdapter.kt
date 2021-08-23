package com.bagicode.foodmarketkotlin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeAdapter(
    private val listData: List<Products>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : Products, itemAdapterCallback : ItemAdapterCallback) {
            itemView.apply {
                tvTitle.text = data.title
//                rbFood.rating = data.rate?.toFloat() ?: 0f
                Glide.with(context)
                    .load(data.image)
                    .into(ivPoster)
                itemView.setOnClickListener { view -> itemAdapterCallback.onClick(view, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View?, data:Products)
    }
}