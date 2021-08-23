package com.bagicode.foodmarketkotlin.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bagicode.foodmarketkotlin.R
import com.bagicode.foodmarketkotlin.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val auth = Intent(this@SplashScreenActivity, AuthActivity::class.java)
            startActivity(auth)
            finish()
        }, 3000)
    }
}