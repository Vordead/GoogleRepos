package com.example.googlerepos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.googlerepos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(_binding.root)
    }

}