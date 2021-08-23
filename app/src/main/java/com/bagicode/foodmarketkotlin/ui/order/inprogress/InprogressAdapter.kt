package com.bagicode.foodmarketkotlin.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.transaction.Data
import com.bagicode.foodmarketkotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_inprogress.view.ivPoster
import kotlinx.android.synthetic.main.item_inprogress.view.tvPrice
import kotlinx.android.synthetic.main.item_inprogress.view.tvTitle

class InprogressAdapter(private val listData: ArrayList<Data>,
                        private val itemAdapterCallback: ItemAdapterCallback
) :
    RecyclerView.Adapter<InprogressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_inprogress, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : Data, itemAdapterCallback : ItemAdapterCallback) {
            itemView.apply {

                tvTitle.text = data.food.name
                tvPrice.formatPrice(data?.food.price.toString())
                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener { view -> itemAdapterCallback.onClick(view, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View?, data: Data)
    }
}