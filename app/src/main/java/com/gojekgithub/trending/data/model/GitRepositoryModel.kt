package com.gojekgithub.trending.data.model

import com.google.gson.annotations.SerializedName

data class GitRepositoryModel (
    @SerializedName("full_name") val fullName : String? = null,
    @SerializedName("name") val name : String? = null,
    @SerializedName("html_url") val url : String? = null,
    @SerializedName("description") val description : String? = null,
    @SerializedName("language") val language : String? = null,
    @SerializedName("stargazers_count") val stars : Int,
    @SerializedName("forks_count") val forks : Int,
    @SerializedName("owner") val owner : Owner? = null,
    var expanded : Boolean?= false
)