package com.bagicode.foodmarketkotlin.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.model.response.home.Data
import com.bagicode.foodmarketkotlin.model.response.home.Products
import com.bagicode.foodmarketkotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.ivPoster
import kotlinx.android.synthetic.main.fragment_detail.tvTitle

class DetailFragment : Fragment() {

    var data:Data?= null
    var bundle:Bundle?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity?)!!.toolbarDetail()

//        data = requireActivity().intent.getParcelableExtra("data")
//        initView(data)

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }

        btnOrderNow.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Products?) {

        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.image)
            .into(ivPoster)

        tvTitle.text = Html.fromHtml(data?.title)
        tvDesc.text = Html.fromHtml(data?.content)
        tvIngredients.text = data?.slug

        tvTotal.formatPrice(data?.price.toString())
    }
}

