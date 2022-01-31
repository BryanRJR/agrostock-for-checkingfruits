package com.argostock.capstoneapp.ui.recyclereinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.argostock.capstoneapp.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /**get Data*/
        val fruitIntent = intent
        val fruitName = fruitIntent.getStringExtra("name")
        val fruitInfo = fruitIntent.getStringExtra("info")
        val fruitImg = fruitIntent.getStringExtra("img")

        /**call text and images*/
        name.text = fruitName
        info.text = fruitInfo
        img.loadImage(fruitImg, getProgessDrawable(this))
    }
}