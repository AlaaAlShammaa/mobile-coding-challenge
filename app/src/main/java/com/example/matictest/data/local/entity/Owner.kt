package com.example.matictest.data.local.entity

import com.google.gson.annotations.SerializedName

data class Owner(var login: String,
                 @SerializedName("avatar_url")
                 var avatarUrl: String)
