package com.example.matictest.data.remote.model

import com.example.matictest.data.local.entity.GithubEntity
import com.google.gson.annotations.SerializedName


data class GithubApiResponse(
    @SerializedName("total_count")
    var totalCount: Long, var items: List<GithubEntity>
)
