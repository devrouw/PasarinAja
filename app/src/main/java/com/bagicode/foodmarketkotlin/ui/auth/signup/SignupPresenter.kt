package com.bagicode.foodmarketkotlin.ui.auth.signup

import android.net.Uri
import android.view.View
import com.bagicode.foodmarketkotlin.model.request.RegisterRequest
import com.bagicode.foodmarketkotlin.network.HttpClient
import com.bagicode.foodmarketkotlin.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignupPresenter (private val view:SignupContract.View) : SignupContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParams: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.success) {
                        view.onRegisterSuccess(it, viewParams)
                    } else {
                        view.onRegisterFailed("Register Gagal")
                    }

                },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun submitPhotoRegister(filePath:Uri, viewParams: View) {
        view.showLoading()

        var profileImageFile = File(filePath.path)

        val profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile)
        var profileImageParams = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody )

        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(
            profileImageParams
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.meta?.status.equals("success")) {
                        it.data?.let { data -> view.onRegisterPhotoSuccess(viewParams) }
                    } else {
                        view.onRegisterFailed(it.meta?.message.toString())
                    }

                },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}