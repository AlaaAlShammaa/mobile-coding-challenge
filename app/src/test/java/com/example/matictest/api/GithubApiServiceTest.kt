package com.example.matictest.api

import com.example.matictest.data.remote.api.GithubApiService
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.io.IOException

import retrofit2.Response

class GithubApiServiceTest : ApiAbstract<GithubApiService>() {

    private lateinit var service: GithubApiService

    @Before
    fun initService() {
        this.service = createService(GithubApiService::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun fetchPostsTest() {
        enqueueResponse("test_repositories.json")
        val response = service.fetchRepositories("stars", "desc", 1L).blockingFirst()
        Assert.assertEquals(true, response.isSuccessful)

        val apiResponse = response.body()
        Assert.assertEquals(1992127L, apiResponse?.totalCount)
        Assert.assertEquals(20, apiResponse?.items?.size)
    }
}
