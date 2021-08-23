package com.bagicode.foodmarketkotlin.ui.order

import com.bagicode.foodmarketkotlin.network.HttpClient
import com.bagicode.foodmarketkotlin.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter (private val view:OrderContract.View) : OrderContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.transaction()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.meta?.status.equals("success")) {
                        it.data?.let { data -> view.onTransactionSuccess(data) }
                    } else {
                        view.onTransactionFailed(it.meta?.message.toString())
                    }

                },
                {
                    view.dismissLoading()
                    view.onTransactionFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}