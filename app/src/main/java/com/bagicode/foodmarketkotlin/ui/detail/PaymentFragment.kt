package com.bagicode.foodmarketkotlin.ui.detail

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
import com.bagicode.foodmarketkotlin.model.response.checkout.CheckoutResponse
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.model.response.login.User
import com.bagicode.foodmarketkotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*


class PaymentFragment : Fragment(), PaymentContract.View {

    var progressDialog: Dialog? = null
    var total : Int = 0
    lateinit var presenter: PaymentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity?)!!.toolbarPayment()

        var data = arguments?.getParcelable<Products>("data")
        initView(data)

        initView()
        presenter = PaymentPresenter(this)

    }

    private fun initView(data: Products?) {
        tvTitle.text = data?.title
        tvPrice.formatPrice(data?.price.toString())
        Glide.with(requireContext())
            .load(data?.image)
            .into(ivPoster)

        tvNameItem.text = data?.title
        tvHarga.formatPrice(data?.price.toString())

        if (!data?.price.toString().isNullOrEmpty()) {
            var totalTax = data?.price?.div(10)
            tvTax.formatPrice(totalTax.toString())

            total = data?.price!! + totalTax!! + 50000
            tvTotal.formatPrice(total.toString())
        } else {
            tvPrice.text = "IDR. 0"
            tvTax.text = "IDR. 0"
            tvTotal.text = "IDR. 0"
            total = 0
        }

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        tvNama.text = userResponse?.name
//        tvPhone.text = userResponse?.phoneNumber
//        tvAddress.text = userResponse?.address
//        tvHouseNo.text = userResponse?.houseNumber
//        tvCity.text = userResponse?.city

        btnCheckout.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(), it
            )
        }

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

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}