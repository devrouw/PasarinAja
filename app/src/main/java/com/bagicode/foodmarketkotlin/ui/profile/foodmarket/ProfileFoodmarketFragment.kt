package com.bagicode.foodmarketkotlin.ui.profile.foodmarket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.ui.profile.ProfileMenuAdapter
import com.bagicode.foodmarketkotlin.ui.profile.ProfileMenuModel
import kotlinx.android.synthetic.main.fragment_profile_foodmarket.*
import java.util.*
import kotlin.collections.ArrayList

class ProfileFoodmarketFragment : Fragment() {
    
    private var adapter: ProfileMenuAdapter? = null
    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_foodmarket, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addData()
        adapter = ProfileMenuAdapter(menuArrayList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun addData() {
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Rate App"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Policy"))
        menuArrayList.add(ProfileMenuModel("Terms & Conditions"))
    }
}