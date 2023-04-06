package com.example.googlerepos.features.google_repos.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.googlerepos.databinding.FragmentGoogleReposBinding
import com.example.googlerepos.features.google_repos.viewmodel.GoogleReposViewModel

class GoogleReposFragment : Fragment() {
private lateinit var binding: FragmentGoogleReposBinding
    companion object {
        fun newInstance() = GoogleReposFragment()
    }

    private lateinit var viewModel: GoogleReposViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleReposBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener{
            binding.button.text = "Hello"
        }

    }

}