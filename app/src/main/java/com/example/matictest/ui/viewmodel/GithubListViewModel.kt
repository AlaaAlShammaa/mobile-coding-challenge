package com.example.matictest.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.matictest.data.SingleLiveEvent
import com.example.matictest.data.local.entity.GithubEntity
import com.example.matictest.data.remote.api.GithubApiService
import com.example.matictest.data.repository.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import java.util.ArrayList

import javax.inject.Inject

class GithubListViewModel @Inject
constructor(githubApiService: GithubApiService) : ViewModel() {

    private var currentPage: Long = 0L
    private var totalCount: Long = 0L
    private val repository: GithubRepository = GithubRepository(githubApiService)

    private val repositories = ArrayList<GithubEntity>()
    private val repositoryListLiveData = SingleLiveEvent<List<GithubEntity>>()


    fun fetchRepositories() {
        repository.getRepositories((++currentPage))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resource ->
                    if (resource.isLoaded) {
                        resource.data?.let {
                            if (totalCount == 0L) {
                            }
                            totalCount = it.totalCount
                            repositories.addAll(it.items)
                            repositoryListLiveData.postValue(it.items)
                        }
                    }
                }, { throwable -> throwable.printStackTrace() })
    }

    fun isLastPage(): Boolean {
        if (currentPage < totalCount) {
            return false
        }
        return true
    }

    fun getRepositoryLiveData() = repositoryListLiveData

    fun getRepositories(): List<GithubEntity> {
        return repositories
    }
}
