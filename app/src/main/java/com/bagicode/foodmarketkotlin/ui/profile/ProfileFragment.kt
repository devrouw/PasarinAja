package com.bagicode.foodmarketkotlin.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bagicode.foodmarketkotlin.FoodMarket
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.ProfileResponse
import com.bagicode.foodmarketkotlin.model.response.login.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.tabLayout
import kotlinx.android.synthetic.main.fragment_profile.viewPager

class ProfileFragment : Fragment() , ProfileContract.View {

    lateinit var presenter: ProfilePresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = ProfilePresenter(this)
        presenter.getProfile()

        //=============
        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvName.setText(userResponse.name)
        tvEmail.setText(userResponse.email)
//        if (!userResponse.profilePhotoUrl.isNullOrEmpty()) {
//            Glide.with(requireActivity())
//                .load(userResponse.profilePhotoUrl)
//                .into(ivPicture)
//        }
        //=============

        val sectionsPagerAdapter = SectionsPagerAdapter(
            childFragmentManager, 2
        )
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = sectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onProfileSuccess(profileResponse: ProfileResponse) {
        tvName.setText(profileResponse.name)
        tvEmail.setText(profileResponse.email)
        Glide.with(this)
            .load(profileResponse.profilePhotoUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(ivPicture)
    }

    override fun onProfileFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
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

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}