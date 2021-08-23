package com.bagicode.foodmarketkotlin.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.bagicode.foodmarketkotlin.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        

//        int pageRequest = getIntent().getIntExtra("page_request", 0);
//
//        if (pageRequest == Cons.AUTH_SIGN_UP) {
//
//            toolbarSignUp();
//
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.fragmentSignIn, true)
//                    .build();
//
//            Navigation.findNavController(findViewById(R.id.authHostFragment))
//                    .navigate(R.id.action_signup, null, navOptions);
//        }

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
        }
    }

    fun toolbarPayment() {
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Payment"
        toolbar.subtitle = "You deserve better meal"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarDetail() {
        toolbar.visibility = View.GONE
    }
}