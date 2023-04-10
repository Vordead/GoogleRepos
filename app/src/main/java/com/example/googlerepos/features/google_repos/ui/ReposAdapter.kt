package com.example.googlerepos.features.google_repos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.googlerepos.databinding.LayoutItemRepositoryBinding
import com.example.googlerepos.features.google_repos.model.RepositoryItem

class RepoListAdapter(private val onItemClick: (RepositoryItem) -> Unit) :
    PagingDataAdapter<RepositoryItem, RepoListAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(private val binding: LayoutItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryItem: RepositoryItem, onItemClick: (RepositoryItem) -> Unit) {
            binding.repositoryItem = repositoryItem
            itemView.setOnClickListener {
                onItemClick(repositoryItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repositoryItem = getItem(position)
        if (repositoryItem != null) {
            holder.bind(repositoryItem, onItemClick)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepositoryItem>() {
            override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean =
                oldItem.full_name == newItem.full_name

            override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean =
                oldItem.id == newItem.id
        }
    }
}