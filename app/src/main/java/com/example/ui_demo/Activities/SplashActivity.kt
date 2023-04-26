package com.example.ui_demo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.ui_demo.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide();
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            delay(3000)
            navigate()
            finish()
        }
    }

    private fun navigate() {
        Intent(this, TabActivity::class.java).let {
            startActivity(it)
        }
    }
}