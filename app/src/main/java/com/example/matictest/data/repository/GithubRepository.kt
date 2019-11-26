package com.example.matictest.data.repository

import com.example.matictest.AppConstants.Companion.QUERY_ORDER
import com.example.matictest.AppConstants.Companion.QUERY_SORT
import com.example.matictest.data.Resource
import com.example.matictest.data.remote.api.GithubApiService
import com.example.matictest.data.remote.model.GithubApiResponse
import io.reactivex.Observable
import javax.inject.Singleton


@Singleton
class GithubRepository(private val githubApiService: GithubApiService) {

    fun getRepositories(page: Long): Observable<Resource<GithubApiResponse>> {
        return githubApiService.fetchRepositories(QUERY_SORT, QUERY_ORDER, page).flatMap { response ->
            Observable.just(if (response.isSuccessful)
                Resource.success(response.body()!!)
            else
                Resource.error("", GithubApiResponse(0, emptyList())))
        }
    }

}
