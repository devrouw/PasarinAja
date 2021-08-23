package com.bagicode.foodmarketkotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.ui.home.newtaste.HomeNewtasteFragment
import com.bagicode.foodmarketkotlin.ui.home.popular.HomePopularFragment
import com.bagicode.foodmarketkotlin.ui.home.recommended.HomeRecomendedFragment

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(
        fm
    ) {

    var newStateList: ArrayList<Products>? = ArrayList()
    var popularList: ArrayList<Products>? = ArrayList()
    var recomendedList: ArrayList<Products>? = ArrayList()

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment
        return when (position) {
            0 -> {
                    fragment = HomeNewtasteFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", newStateList)
                    fragment.setArguments(bundle)
                    return fragment
            }
            1 -> {
                    fragment = HomePopularFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", popularList)
                    fragment.setArguments(bundle)
                    return fragment
                }
            2 -> {
                    fragment = HomeRecomendedFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", recomendedList)
                    fragment.setArguments(bundle)
                    return fragment
                }
            else -> {
                fragment = HomeNewtasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newStateList)
                fragment.setArguments(bundle)
                return fragment
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Sayuran"
            1 -> "Daging"
            2 -> "Buah-Buahan"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    fun setData(newStateListParms: ArrayList<Products>?, popularListParms: ArrayList<Products>?, recomendedListParms: ArrayList<Products>?) {
        newStateList = newStateListParms
        popularList = popularListParms
        recomendedList = recomendedListParms
    }
}