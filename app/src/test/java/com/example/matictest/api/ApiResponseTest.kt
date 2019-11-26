package com.example.matictest.api

import com.example.matictest.data.Resource
import com.example.matictest.data.Status

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("test")
        val apiResponse = Resource.error(exception.message!!, exception)
        Assert.assertEquals("test", apiResponse.message)
        Assert.assertEquals(Status.ERROR, apiResponse.status)
    }

    @Test
    fun success() {
        val resource = Resource.success("test")
        Assert.assertEquals("test", resource.data)
        Assert.assertEquals(Status.SUCCESS, resource.status)
    }
}
