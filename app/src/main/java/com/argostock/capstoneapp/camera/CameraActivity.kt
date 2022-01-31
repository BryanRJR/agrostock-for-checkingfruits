package com.argostock.capstoneapp.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.databinding.ActivityCameraBinding
import com.argostock.capstoneapp.databinding.ActivityNewsBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var cameraBinding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraBinding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(cameraBinding.root)

        navigationChange(CameraFragment())

        cameraBinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}