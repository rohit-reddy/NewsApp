
package com.rohith.rohithltiassessment

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rohith.rohithltiassessment.network.Source
import com.rohith.rohithltiassessment.article.NewsApiStatus
import com.rohith.rohithltiassessment.article.ArticleAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Source>?) {

    val adapter = recyclerView.adapter as ArticleAdapter
    adapter.submitList(data)
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: NewsApiStatus?) {

    when (status) {
        NewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        NewsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

