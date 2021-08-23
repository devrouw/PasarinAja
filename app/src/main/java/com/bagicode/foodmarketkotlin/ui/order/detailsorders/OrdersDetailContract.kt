package com.bagicode.foodmarketkotlin.ui.order.detailsorders

import com.bagicode.foodmarketkotlin.base.BasePresenter
import com.bagicode.foodmarketkotlin.base.BaseView
import com.bagicode.foodmarketkotlin.model.response.ProfileResponse
import com.bagicode.foodmarketkotlin.model.response.login.LoginResponse
import com.bagicode.foodmarketkotlin.model.response.transaction.TransactionResponse

interface OrdersDetailContract {
    interface View : BaseView {
        fun onUpdateTransactionSuccess(message: String)
        fun onUpdateTransactionFailed(message: String)
    }

    interface Presenter : OrdersDetailContract, BasePresenter {
        fun getUpdateTransaction(id:String, status:String)
    }
}