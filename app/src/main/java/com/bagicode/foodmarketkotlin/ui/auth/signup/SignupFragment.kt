package com.bagicode.foodmarketkotlin.ui.auth.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bagicode.foodmarketkotlin.FoodMarket
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.request.RegisterRequest
import com.bagicode.foodmarketkotlin.model.response.login.LoginResponse
import com.bagicode.foodmarketkotlin.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.etEmail
import kotlinx.android.synthetic.main.fragment_signup.etPassword

class SignupFragment : Fragment(), SignupContract.View {

    lateinit var presenter : SignupPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SignupPresenter(this)
        initDummy()
        initView()
        initListener()
    }

    private fun initDummy() {
        etFullName.setText("Jennie Kim White")
        etEmail.setText("jennie.kim@blackwhitepink3.com")
        etPassword.setText("12345678")
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initListener() {
//        ivProfile.setOnClickListener {
//            ImagePicker.with(this)
//                .cameraOnly()
//                .start()
//        }

        btnContinue.setOnClickListener {
            var fullname = etFullName.text.toString()
            var email = etEmail.text.toString()
            var pass = etPassword.text.toString()

            if (fullname.isNullOrEmpty()) {
                etFullName.error = "Silahkan masukkan Fullname"
                etFullName.requestFocus()
            } else if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan Email"
                etEmail.requestFocus()
            } else if (pass.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan Password"
                etPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    fullname,
                    email,
                    pass,
                    pass
                )
                view?.let { it1 -> presenter.submitRegister(data, it1) }
            }

        }
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        Navigation.findNavController(view).navigate(R.id.action_signup_success)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterPhotoSuccess(view: View) {

    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}