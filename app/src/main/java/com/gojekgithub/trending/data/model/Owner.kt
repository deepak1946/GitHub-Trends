package com.gojekgithub.trending.data.model
import com.google.gson.annotations.SerializedName

data class Owner (
	@SerializedName("url") val url : String,
	@SerializedName("avatar_url") val avatar : String
)