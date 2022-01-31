package com.argostock.capstoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.argostock.capstoneapp.camera.CameraActivity
import com.argostock.capstoneapp.dashboard.NewsActivity
import com.argostock.capstoneapp.login.LoginActivity
import com.argostock.capstoneapp.ui.recyclereinfo.FruitsActivity
import com.bumptech.glide.Glide
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<SwitchMaterial>(R.id.mode).setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                setTheme("night")
            }else{
                setTheme("day")
            }
        }

        val news : CardView = findViewById(R.id.berita)
        val scan : CardView = findViewById(R.id.scanner)
        val info : CardView = findViewById(R.id.menu1)

        news.setOnClickListener{
            startActivity(Intent(this, NewsActivity::class.java))
        }
        scan.setOnClickListener{
            startActivity(Intent(this, CameraActivity::class.java))
        }
        info.setOnClickListener{
            startActivity(Intent(this, FruitsActivity::class.java))
        }

        //Profil Pic and Logout
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        id_txt.text = currentUser?.email
        name_txt.text = currentUser?.displayName

        Glide.with(this).load(currentUser?.photoUrl).into(profile_image)

        sign_out_btn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setTheme( themeCode: String) {
        if (themeCode.equals("day", ignoreCase = true)) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        }
    }
}