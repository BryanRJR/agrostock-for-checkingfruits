package com.argostock.capstoneapp.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.argostock.capstoneapp.R
import com.argostock.capstoneapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var newsBinding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(newsBinding.root)

        newsBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val webv : WebView = findViewById(R.id.webView)

        webv.settings.javaScriptCanOpenWindowsAutomatically = true
        webv.loadUrl("https://superapp.id/blog/lifestyle/cara-ampuh-memilih-buah-buahan-segar/")
    }
}