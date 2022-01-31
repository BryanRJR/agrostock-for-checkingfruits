package com.argostock.capstoneapp.flashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.argostock.capstoneapp.MainActivity
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class LoadingActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if(user != null){
                val dashboardIntent = Intent(this, MainActivity::class.java)
                startActivity(dashboardIntent)
                finish()
            }else{
                val signInIntent = Intent(this, LoginActivity::class.java)
                startActivity(signInIntent)
                finish()
            }
        }, 2000)
    }
}