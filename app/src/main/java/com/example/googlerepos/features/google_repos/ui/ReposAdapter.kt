package com.example.googlerepos.features.google_repos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.googlerepos.R
import com.example.googlerepos.databinding.LayoutItemViewBinding
import com.example.googlerepos.features.google_repos.model.RepositoryItem

class RepoListAdapter(private val onItemClick: (RepositoryItem) -> Unit) :
    PagingDataAdapter<RepositoryItem, RepoListAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(private val binding: LayoutItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryItem: RepositoryItem, onItemClick: (RepositoryItem) -> Unit) {
            binding.repoNameText.text = repositoryItem.full_name
            binding.creationDateText.text = repositoryItem.created_at
            val imgUri = repositoryItem.owner?.avatar_url?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.ownerAvatar.load(imgUri) {
                placeholder(R.drawable.ic_person_placeholder)
                error(R.drawable.ic_launcher_foreground)
            }

            itemView.setOnClickListener {
                onItemClick(repositoryItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemViewBinding.inflate(
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
                oldItem == newItem
        }
    }
}