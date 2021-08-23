package com.bagicode.foodmarketkotlin.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bagicode.foodmarketkotlin.FoodMarket
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.login.LoginResponse
import com.bagicode.foodmarketkotlin.ui.MainActivity
import com.bagicode.foodmarketkotlin.ui.auth.AuthActivity
import com.bagicode.foodmarketkotlin.utils.Cons
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*


class SigninFragment : Fragment(), SigninContract.View {

    lateinit var presenter : SigninPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SigninPresenter(this)
        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

//        initDummy()
        initView()
        initListener()

    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun initDummy() {
//        etEmail.setText("jennie.kim@blackpink.com")
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
        btnSignin!!.setOnClickListener {
            var email = etEmail.text.toString()
            var pass = etPassword.text.toString()

            if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan Email"
                etEmail.requestFocus()
            } else if (pass.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan Password"
                etPassword.requestFocus()
            } else {
                presenter.submitLogin(email, pass)
            }

        }

        btnSignup!!.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", Cons.AUTH_SIGN_UP)
            startActivity(signup)
        }
    }
}
