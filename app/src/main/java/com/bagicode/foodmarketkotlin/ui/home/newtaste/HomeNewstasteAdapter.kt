package com.bagicode.foodmarketkotlin.ui.home.newtaste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_home_vertical.view.*

//class HomeNewstasteAdapter (
//    private val listData: ArrayList<HomeNewtasteModel>,
//    private val itemAdapterCallback: ItemAdapterCallback
//) : RecyclerView.Adapter<HomeNewstasteAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(listData[position], itemAdapterCallback)
//
//    }
//
//    override fun getItemCount(): Int {
//        return listData.size
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(data : HomeNewtasteModel, itemAdapterCallback : ItemAdapterCallback) {
//            itemView.apply {
//                tvTitle.text = data.title
//                tvPrice.text = data.subtitle
//                rbFood.rating = data.rating
//                itemView.setOnClickListener { view -> itemAdapterCallback.onClick(view) }
//            }
//        }
//    }
//
//    interface ItemAdapterCallback {
//        fun onClick(v: View?)
//    }
//}
class HomeNewstasteAdapter (
    private val listData: ArrayList<Products>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<HomeNewstasteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
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
                tvPrice.formatPrice(data?.price.toString())
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