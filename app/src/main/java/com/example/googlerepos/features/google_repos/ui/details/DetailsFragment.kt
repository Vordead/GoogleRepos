package com.example.googlerepos.features.google_repos.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.googlerepos.base_mvvm.BaseFragment
import com.example.googlerepos.databinding.FragmentDetailsBinding
import com.example.googlerepos.features.google_repos.model.RepositoryItem

const val ARG_REPO_ITEM = "arg_repo_item"

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private lateinit var repositoryItem: RepositoryItem
    private lateinit var binding: FragmentDetailsBinding

    companion object {
        fun newInstance(
            repositoryItem: RepositoryItem,
        ): DetailsFragment {
            val args = Bundle()
            args.putParcelable(ARG_REPO_ITEM, repositoryItem)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            repositoryItem = it.getParcelable(ARG_REPO_ITEM)!!
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.repositoryItem = repositoryItem
        return binding.root
    }
}