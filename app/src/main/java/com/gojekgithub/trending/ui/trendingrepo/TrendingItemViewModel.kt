package com.gojekgithub.trending.ui.trendingrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gojekgithub.trending.data.model.GitRepositoryModel
import java.text.NumberFormat
import java.util.*

class TrendingItemViewModel constructor(
    private val trendingRepoData: GitRepositoryModel
) : ViewModel() {
    private val author: MutableLiveData<String> = MutableLiveData()
    private val title: MutableLiveData<String> = MutableLiveData()
    private val avatar: MutableLiveData<String> = MutableLiveData()
    private val description: MutableLiveData<String> = MutableLiveData()
    private val language: MutableLiveData<String> = MutableLiveData()
    private val stars: MutableLiveData<String> = MutableLiveData()
    private val forks: MutableLiveData<String> = MutableLiveData()
    private val expanded: MutableLiveData<Boolean> = MutableLiveData()

    init {
        trendingRepoData.also { trendingRepoData ->
            author.value = trendingRepoData.fullName
            title.value = trendingRepoData.name
            avatar.value = trendingRepoData.owner?.avatar
            description.value = "${trendingRepoData.description}(${trendingRepoData.url})"
            language.value = trendingRepoData.language
            stars.value = NumberFormat.getNumberInstance(Locale.US).format(trendingRepoData.stars)
            forks.value = NumberFormat.getNumberInstance(Locale.US).format(trendingRepoData.forks)
            expanded.value = trendingRepoData.expanded?: false
        }
    }

    fun getStars(): LiveData<String> {
        return stars
    }

    fun getForks(): LiveData<String> {
        return forks
    }

    fun getImageUrl(): LiveData<String> {
        return avatar
    }

    fun getDescription(): LiveData<String> {
        return description
    }

    fun getLanguage(): LiveData<String> {
        return language
    }

    fun getTitle(): LiveData<String> {
        return title
    }

    fun getAuthor(): LiveData<String> {
        return author
    }

    fun getExpanded(): LiveData<Boolean> {
        return expanded
    }

    fun setExpanded() {
        val expandedValue = !(this.expanded.value ?: false)
        trendingRepoData.expanded = expandedValue
        this.expanded.value = expandedValue
    }
}