package com.bagicode.foodmarketkotlin.ui.profile

import com.bagicode.foodmarketkotlin.base.BasePresenter
import com.bagicode.foodmarketkotlin.base.BaseView
import com.bagicode.foodmarketkotlin.model.response.ProfileResponse
import com.bagicode.foodmarketkotlin.model.response.login.LoginResponse

interface ProfileContract {
    interface View : BaseView {
        fun onProfileSuccess(profileResponse: ProfileResponse)
        fun onProfileFailed(message: String)
    }

    interface Presenter : ProfileContract, BasePresenter {
        fun getProfile()
    }
}