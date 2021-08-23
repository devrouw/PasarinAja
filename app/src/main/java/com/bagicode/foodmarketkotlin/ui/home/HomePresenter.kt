package com.bagicode.foodmarketkotlin.ui.home

import com.bagicode.foodmarketkotlin.network.HttpClient
import com.bagicode.foodmarketkotlin.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val view:HomeContract.View) : HomeContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.products()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.success) {
                        view.onHomeSuccess(it)
                    } else {
                        view.onHomeFailed(it.message)
                    }

                },
                {
                    view.dismissLoading()
                    view.onHomeFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}