
package com.rohith.rohithltiassessment.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohith.rohithltiassessment.databinding.GridViewItemBinding
import com.rohith.rohithltiassessment.network.Source


class ArticleAdapter(private val onClickListener: OnClickListener ) : ListAdapter<Source,
        ArticleAdapter.NewsArticleViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.NewsArticleViewHolder {
        return NewsArticleViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleAdapter.NewsArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(article)
        }
        holder.bind(article)
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Source>() {

        override fun areItemsTheSame(oldItem: Source,
                                     newItem: Source): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Source,
                                        newItem: Source): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class NewsArticleViewHolder(private var binding:
                                 GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(source: Source) {
            binding.article = source
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (source:Source) -> Unit) {
        fun onClick(source:Source) = clickListener(source)
    }
}

