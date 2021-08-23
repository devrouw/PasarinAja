package com.bagicode.foodmarketkotlin.ui.detail

import com.bagicode.foodmarketkotlin.base.BasePresenter
import com.bagicode.foodmarketkotlin.base.BaseView
import com.bagicode.foodmarketkotlin.model.response.checkout.CheckoutResponse

interface PaymentContract {
    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId : String, userId : String, quantity : String, total : String, view : android.view.View)
    }
}