package com.example.matictest.data.remote.api

import com.example.matictest.data.remote.model.GithubApiResponse

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/repositories")
    fun fetchRepositories(@Query("sort") sort: String,
                          @Query("order") order: String,
                          @Query("page") page: Long): Observable<Response<GithubApiResponse>>
}
