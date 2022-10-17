package com.gojekgithub.trending.data.api

import com.gojekgithub.trending.data.model.GitRepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TrendingApiService {
    companion object {
        const val HEADER_FORCE_REMOTE = "custom_force_remote"
    }

    @GET("repos")
    suspend fun getRepositories(
        @Header(HEADER_FORCE_REMOTE) forceRefresh : String = "false",
    ): Response<List<GitRepositoryModel>>

}