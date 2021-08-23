package com.bagicode.foodmarketkotlin.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_signup_success.*

class SignupSuccessFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnFind.setOnClickListener {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finishAffinity()
        }
    }
}