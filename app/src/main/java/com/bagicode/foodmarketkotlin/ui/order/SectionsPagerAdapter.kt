package com.bagicode.foodmarketkotlin.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bagicode.foodmarketkotlin.model.response.transaction.Data
import com.bagicode.foodmarketkotlin.ui.profile.account.InprogressFragment
import com.bagicode.foodmarketkotlin.ui.profile.account.PastordersFragment

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(
        fm
    ) {

    var inprogressList: ArrayList<Data>? = ArrayList()
    var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment
        return when (position) {
            0 -> {
                    fragment = InprogressFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", inprogressList)
                    fragment.setArguments(bundle)
                    return fragment
            }
            1 -> {
                    fragment = PastordersFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", pastOrdersList)
                    fragment.setArguments(bundle)
                    return fragment
                }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.setArguments(bundle)
                return fragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    fun setData(inprogressListParms: ArrayList<Data>?, pastOrdersListParms: ArrayList<Data>?) {
        inprogressList = inprogressListParms
        pastOrdersList = pastOrdersListParms
    }
}