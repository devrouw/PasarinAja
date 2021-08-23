package com.bagicode.foodmarketkotlin.ui.detail

import android.view.View
import com.bagicode.foodmarketkotlin.network.HttpClient
import com.bagicode.foodmarketkotlin.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter (private val view:PaymentContract.View) : PaymentContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getCheckout(foodId : String, userId : String, quantity : String,
                             total : String, viewParms: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
                foodId,
                userId,
                quantity,
                total,
                "DELIVERED")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.meta?.status.equals("success")) {
                        it.data?.let { data -> view.onCheckoutSuccess(data, viewParms) }
                    } else {
                        view.onCheckoutFailed(it.meta?.message.toString())
                    }

                },
                {
                    view.dismissLoading()
                    view.onCheckoutFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}