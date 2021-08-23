package com.bagicode.foodmarketkotlin.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bagicode.foodmarketkotlin.ui.profile.account.PastordersFragment
import com.bagicode.foodmarketkotlin.ui.profile.account.ProfileAccountFragment
import com.bagicode.foodmarketkotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class SectionsPagerAdapter(fm: FragmentManager?, private val number_tabs: Int) :
    FragmentPagerAdapter(
        fm!!
    ) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProfileAccountFragment()
            1 -> ProfileFoodmarketFragment()
            else -> PastordersFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> null
        }
    }

    override fun getCount(): Int {
        return number_tabs
    }
}