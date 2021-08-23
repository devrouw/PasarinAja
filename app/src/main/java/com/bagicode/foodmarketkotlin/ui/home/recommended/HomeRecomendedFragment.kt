package com.bagicode.foodmarketkotlin.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.ui.detail.DetailActivity
import com.bagicode.foodmarketkotlin.ui.home.newtaste.HomeNewstasteAdapter
import kotlinx.android.synthetic.main.fragment_home_newtaste.*
import java.util.*
import kotlin.collections.ArrayList

class HomeRecomendedFragment : Fragment(), HomeNewstasteAdapter.ItemAdapterCallback {

    private var adapter: HomeNewstasteAdapter? = null
    private var newTasteList: ArrayList<Products>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_newtaste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        addData()

        newTasteList = arguments?.getParcelableArrayList("data")

        adapter = HomeNewstasteAdapter(newTasteList!!, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

//    fun addData() {
//        menuArrayList = ArrayList()
//        menuArrayList!!.add(HomeNewtasteModel("Soup Bumil", "IDR 289.000", "", 4f))
//        menuArrayList!!.add(HomeNewtasteModel("Chicken", "IDR 4.509.000", "", 3f))
//        menuArrayList!!.add(HomeNewtasteModel("Chicken Fill", "IDR 109.000", "", 5f))
//    }

    override fun onClick(v: View?, data: Products) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}