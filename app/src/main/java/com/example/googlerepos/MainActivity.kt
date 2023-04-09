package com.example.googlerepos

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.googlerepos.databinding.ActivityMainBinding
import com.example.googlerepos.features.google_repos.ui.GoogleReposFragment


class MainActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }
        }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, GoogleReposFragment.newInstance())
            .commit()
    }

}