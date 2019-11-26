package com.example.matictest.data.local.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.example.matictest.data.local.converter.TimestampConverter
import com.google.gson.annotations.SerializedName

data class GithubEntity(@PrimaryKey
                   val id: Long,
                   var name: String,
                   @SerializedName("full_name")
                   var fullName: String,
                   @Embedded
                   var owner: Owner,
                   @SerializedName("html_url")
                   var htmlUrl: String,
                   var description: String,
                   @SerializedName("contributors_url")
                   var contributorsUrl: String,
                   @TypeConverters(TimestampConverter::class)
                   @SerializedName("created_at")
                   var createdAt: String,
                   @SerializedName("stargazers_count")
                   var starsCount: Long,
                   var watchers: Long,
                   var forks: Long,
                   var language: String?)
