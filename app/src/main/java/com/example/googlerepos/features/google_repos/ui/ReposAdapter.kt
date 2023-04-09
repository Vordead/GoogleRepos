package com.example.googlerepos.features.google_repos.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.googlerepos.databinding.ItemRepositoryBinding
import com.example.googlerepos.features.google_repos.model.Repository
import com.example.googlerepos.features.google_repos.model.RepositoryItem

//class ReposAdapter(
//    val data: List<RepositoryItem>,
//    val listener: RepoItemListener,
//): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//
//    abstract class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        abstract fun bind(clickListener: RepoItemListener, item: RepositoryItem)
//    }
//
//    inner class RepoViewHolder constructor(private val binding: ItemRepositoryBinding) :
//        ViewHolder(binding.root) {
//        override fun bind(clickListener: RepoItemListener, item: RepositoryItem) {
//            binding.repo = item
//            binding.repoNameText.text = item.name
//            binding.creationDateText.text = item.created_at
//            binding.root.setOnClickListener { listener }
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
//        return RepoViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = data[position]
//        (holder as RepoViewHolder).bind(listener, item)
//    }
//
//
//}


open class RepoItemListener(
    val clickListener: (repositoryItem: RepositoryItem) -> Unit,
) {
    fun onClick(repositoryItem: RepositoryItem) = clickListener(repositoryItem)
}


class ReposAdapter(
    val listener: RepoItemListener
) :
    ListAdapter<RepositoryItem, ReposAdapter.RepoViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsPhoto] information.
     */
    class RepoViewHolder(
        private var binding: ItemRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: RepoItemListener, repositoryItem: RepositoryItem) {
            binding.repo = repositoryItem
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.root.setOnClickListener {
                clickListener.onClick(repositoryItem)
            }
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [MarsPhoto] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RepositoryItem, newItem: RepositoryItem): Boolean {
            return oldItem.owner?.url  == newItem.owner?.url
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepoViewHolder {
        return RepoViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repositoryItem = getItem(position)
        holder.bind(listener,repositoryItem)
    }
}