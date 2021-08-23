package com.bagicode.foodmarketkotlin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bagicode.foodmarketkotlin.R
import kotlinx.android.synthetic.main.fragment_orders_success.*

class PaymentSuccessFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity?)!!.toolbarDetail()

        btnOtherFood.setOnClickListener {
            requireActivity().finish()
        }

        btnMyOrder.setOnClickListener {
            requireActivity().finish()
        }
    }
}