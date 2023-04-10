package com.example.googlerepos.features.google_repos.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.googlerepos.databinding.LayoutLoadingFooterBinding

class RepoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RepoLoadStateAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(binding: LayoutLoadingFooterBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        val errorMsg: TextView = binding.txtErrorMsg
        val progressBar: ProgressBar = binding.progressBar
        val retryButton: Button = binding.btnRetry

        init {
            retryButton.setOnClickListener { retry() }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        if (loadState is LoadState.Error)
            holder.errorMsg.text = loadState.error.localizedMessage
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
        holder.errorMsg.isVisible = loadState is LoadState.Error
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LayoutLoadingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding, retry)
    }
}