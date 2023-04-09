package com.example.googlerepos.features.google_repos.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlerepos.R
import com.example.googlerepos.databinding.FragmentGoogleReposBinding
import com.example.googlerepos.base_mvvm.BaseFragment
import com.example.googlerepos.features.google_repos.ui.details.DetailsFragment
import com.example.googlerepos.utils.kotlin.addFragment

class GoogleReposFragment : BaseFragment<GoogleReposViewModel>() {
    private lateinit var binding: FragmentGoogleReposBinding

    companion object {
        fun newInstance() = GoogleReposFragment()

    }

    override val viewModel: GoogleReposViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleReposBinding.inflate(inflater, container, false)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the GoogleReposViewModel
        binding.viewModel = viewModel


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.fetchRepos()

    }

    private fun setupObservers() {
        viewModel.repos.observe(viewLifecycleOwner) {
            binding.reposList.adapter =
                ReposAdapter(listener = RepoItemListener {
                    addFragment(
                        R.id.container,
                        DetailsFragment.newInstance()
                    )
                })
            binding.reposList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }
}