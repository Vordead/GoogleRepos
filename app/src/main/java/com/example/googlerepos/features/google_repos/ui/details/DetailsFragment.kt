package com.example.googlerepos.features.google_repos.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.googlerepos.base_mvvm.BaseFragment
import com.example.googlerepos.databinding.FragmentDetailsBinding
import com.example.googlerepos.features.google_repos.model.RepositoryItem


const val ARG_VISIT_ITEM = "args_news_item"


class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private lateinit var repositoryItem: RepositoryItem


    private lateinit var binding: FragmentDetailsBinding

    companion object {
        fun newInstance(
            repositoryItem: RepositoryItem,
        ): DetailsFragment {
            val args = Bundle()
            args.putParcelable(ARG_VISIT_ITEM, repositoryItem)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            repositoryItem = it.getParcelable(ARG_VISIT_ITEM)!!
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.starName.text = repositoryItem.name

    }

}