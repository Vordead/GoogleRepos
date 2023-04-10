package com.example.googlerepos.features.google_repos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlerepos.R
import com.example.googlerepos.base_mvvm.BaseFragment
import com.example.googlerepos.databinding.FragmentGoogleReposBinding
import com.example.googlerepos.features.google_repos.ui.details.DetailsFragment
import com.example.googlerepos.utils.kotlin.addFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GoogleReposFragment : BaseFragment<GoogleReposViewModel>() {
    private lateinit var binding: FragmentGoogleReposBinding

    private val repoListAdapter = RepoListAdapter { repositoryItem ->
        addFragment(
            R.id.container,
            DetailsFragment.newInstance(repositoryItem)
        )
    }

    companion object {
        private const val REPO_NAME = "google"

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = repoListAdapter.withLoadStateHeaderAndFooter(
            header = RepoLoadStateAdapter(retry = { viewLifecycleOwner.lifecycleScope.launch { viewModel.retrySearch() } }),
            footer = RepoLoadStateAdapter(retry = { viewLifecycleOwner.lifecycleScope.launch { viewModel.retrySearch() } })
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.searchRepos(REPO_NAME).collectLatest { pagingData ->
                repoListAdapter.submitData(pagingData)
            }
        }
    }
}