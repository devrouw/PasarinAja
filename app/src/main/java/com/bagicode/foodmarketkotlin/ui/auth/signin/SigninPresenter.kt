package com.bagicode.foodmarketkotlin.ui.auth.signin

import android.util.Log
import com.bagicode.foodmarketkotlin.network.HttpClient
import com.bagicode.foodmarketkotlin.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SigninPresenter (private val view:SigninContract.View) : SigninContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.success) {
                        view.onLoginSuccess(it)
                    } else {
                        view.onLoginFailed("Login gagal")
                    }

                },
                {
                    view.dismissLoading()
                    view.onLoginFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}