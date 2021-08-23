package com.bagicode.foodmarketkotlin.ui.profile.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.transaction.Data
import com.bagicode.foodmarketkotlin.model.response.transaction.Food
import com.bagicode.foodmarketkotlin.ui.order.detailsorders.OrdersDetailActivity
import com.bagicode.foodmarketkotlin.ui.order.inprogress.InprogressAdapter
import com.bagicode.foodmarketkotlin.ui.order.inprogress.PastordersAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*
import kotlin.collections.ArrayList

class PastordersFragment : Fragment(), PastordersAdapter.ItemAdapterCallback {
    
    private var adapter: PastordersAdapter? = null
    private var pastodersList: ArrayList<Data>? = ArrayList()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pastorders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastodersList = arguments?.getParcelableArrayList("data")

        if (!pastodersList.isNullOrEmpty()) {
            adapter = PastordersAdapter(pastodersList!!, this)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }
    }

    override fun onClick(v: View?, data: Data) {
        val detail = Intent(activity, OrdersDetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}