package com.gojekgithub.trending.utils

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.gojekgithub.trending.constants.Status
import com.gojekgithub.trending.data.model.GitRepositoryModel
import com.gojekgithub.trending.ui.trendingrepo.TrendingRecyclerViewAdapter


object BindingUtils {

    @BindingAdapter("app:trendingItems", "app:lifecycleOwner")
    @JvmStatic
    fun setTrendingAdapter(view: RecyclerView, items: List<GitRepositoryModel>?, lifecycleOwner: LifecycleOwner) {
        if(items == null) {
            return
        }
        var adapter: TrendingRecyclerViewAdapter? = view.adapter as TrendingRecyclerViewAdapter?
        view.layoutManager = LinearLayoutManager(view.context)
        if (null == adapter) {
            adapter = TrendingRecyclerViewAdapter(items, lifecycleOwner)
            view.adapter = adapter
        } else {
            adapter.setContentItemList(items)
            adapter.notifyDataSetChanged()
        }
    }

    @BindingAdapter("app:loadImage")
    @JvmStatic
    fun loadImage(imageView: AppCompatImageView, imageLink: String?) {
        Glide.with(imageView.context)
            .load(imageLink)
            .into(imageView)
    }

    @BindingAdapter("app:shimmer")
    @JvmStatic
    fun loadImage(shimmerFrameLayout: ShimmerFrameLayout, @Status status: Int?) {
        shimmerFrameLayout.apply {
            if(status == Status.Loading) {
                visibility = VISIBLE
                startShimmer()
            } else {
                stopShimmer()
                visibility = GONE
            }
        }
    }
}