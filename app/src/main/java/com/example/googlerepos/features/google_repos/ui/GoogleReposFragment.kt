package com.example.googlerepos.features.google_repos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlerepos.R
import com.example.googlerepos.base_mvvm.BaseFragment
import com.example.googlerepos.databinding.FragmentGoogleReposBinding
import com.example.googlerepos.features.google_repos.ui.details.DetailsFragment
import com.example.googlerepos.utils.kotlin.addFragment

private const val REPO_NAME = "google"

class GoogleReposFragment : BaseFragment<GoogleReposViewModel>() {
    private lateinit var binding: FragmentGoogleReposBinding

    private val repoListAdapter = RepoListAdapter { repositoryItem ->
        addFragment(
            R.id.container,
            DetailsFragment.newInstance(repositoryItem)
        )
    }

    companion object {
        fun newInstance() = GoogleReposFragment()
    }

    override val viewModel: GoogleReposViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleReposBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = repoListAdapter.withLoadStateHeaderAndFooter(
            header = RepoLoadStateAdapter(retry = { viewModel.retrySearch() }),
            footer = RepoLoadStateAdapter(retry = { viewModel.retrySearch() })
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.searchRepos(REPO_NAME).observe(viewLifecycleOwner) {
            repoListAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        return binding.root
    }
}