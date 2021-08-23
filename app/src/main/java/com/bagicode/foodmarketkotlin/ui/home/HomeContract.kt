package com.bagicode.foodmarketkotlin.ui.home

import com.bagicode.foodmarketkotlin.base.BasePresenter
import com.bagicode.foodmarketkotlin.base.BaseView
import com.bagicode.foodmarketkotlin.model.response.home.HomeResponse
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.model.response.home.ProductsResponse

interface HomeContract {
    interface View : BaseView {
        fun onHomeSuccess(homeResponse: ProductsResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}