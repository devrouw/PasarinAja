package com.bagicode.foodmarketkotlin.ui.home

import android.R.attr.data
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagicode.foodmarketkotlin.FoodMarket
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.HomeResponse
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.model.response.home.ProductsResponse
import com.bagicode.foodmarketkotlin.model.response.login.User
import com.bagicode.foodmarketkotlin.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.zxing.client.result.ProductResultParser
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_inprogress.view.*


class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var adapter: HomeAdapter? = null
    var progressDialog: Dialog? = null
    private var newStateList: ArrayList<Products>? = ArrayList()
    private var popularList: ArrayList<Products>? = ArrayList()
    private var recomendedList: ArrayList<Products>? = ArrayList()

    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = HomePresenter(this)
        presenter.getHome()

    }

//    fun initDataDummy() {
//        menuArrayList = ArrayList()
//        menuArrayList!!.add(HomeModel("Cherry Healthy", "", 5f))
//        menuArrayList!!.add(HomeModel("Burger Tamayo", "", 4f))
//        menuArrayList!!.add(HomeModel("Bakwan Uhay", "", 4.5f))
//    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

//        if (!userResponse.profilePhotoUrl.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load(userResponse.profilePhotoUrl)
//                .into(ivProfil)
//        }

    }

    override fun onClick(v: View?, data: Products) {
        var bundle = Bundle()
        bundle.putParcelable("data", data)
        val detail = Intent(activity, DetailActivity::class.java).putExtras(bundle)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: ProductsResponse) {

        for (a in homeResponse.products.indices) {
            if (homeResponse.products[a].category_id == 3) {
                newStateList?.add(homeResponse.products[a])
            } else if (homeResponse.products[a].category_id == 5) {
                recomendedList?.add(homeResponse.products[a])
            } else if (homeResponse.products[a].category_id == 6) {
                popularList?.add(homeResponse.products[a])
            }
        }

        adapter = HomeAdapter(homeResponse.products, this)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val sectionsPagerAdapter = SectionsPagerAdapter(
            childFragmentManager
        )
        sectionsPagerAdapter.setData(newStateList, popularList, recomendedList)
        viewPager!!.offscreenPageLimit = 3
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}