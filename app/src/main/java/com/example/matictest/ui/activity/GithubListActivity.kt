package com.example.matictest.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.matictest.R
import com.example.matictest.data.local.entity.GithubEntity
import com.example.matictest.databinding.GithubListActivityBinding
import com.example.matictest.factory.ViewModelFactory
import com.example.matictest.ui.adapter.GithubListAdapter
import com.example.matictest.ui.custom.recyclerview.RecyclerViewPaginator
import com.example.matictest.ui.extensions.withNotNullNorEmpty
import com.example.matictest.ui.viewmodel.GithubListViewModel

import javax.inject.Inject

import dagger.android.AndroidInjection

class GithubListActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var binding: GithubListActivityBinding
    private lateinit var githubListViewModel: GithubListViewModel

    private lateinit var githubListAdapter: GithubListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        initialiseViewModel()
        initialiseView()
    }

    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)
        setSupportActionBar(binding.mainToolbar.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        githubListAdapter = GithubListAdapter(applicationContext)
        binding.recyclerView.adapter = githubListAdapter
        binding.recyclerView.addOnScrollListener(object : RecyclerViewPaginator(binding.recyclerView) {
            override val isLastPage: Boolean
                get() = githubListViewModel.isLastPage()

            override fun loadMore() {
                githubListViewModel.fetchRepositories()
            }
        })

        /* This is to handle configuration changes:
         * during configuration change, when the activity
         * is recreated, we check if the viewModel
         * contains the list data. If so, there is no
         * need to call the api or load data from cache again */
        if (githubListViewModel.getRepositories().isEmpty()) {
            displayLoader()
            githubListViewModel.fetchRepositories()
        } else
            animateView(githubListViewModel.getRepositories())
    }


    private fun initialiseViewModel() {
        githubListViewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubListViewModel::class.java)
        githubListViewModel.getRepositoryLiveData().observe(this, Observer { repositories ->
            if (githubListAdapter.itemCount == 0) {
                repositories.withNotNullNorEmpty {
                    animateView(repositories)
                } .otherwise {
                    displayEmptyView()
                }

            } else {
                repositories.withNotNullNorEmpty {
                    displayDataView(repositories)
                }
            }
        })
    }

    private fun displayLoader() {
        binding.viewLoader.rootView.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.viewLoader.rootView.visibility = View.GONE
    }


    private fun animateView(repositories: List<GithubEntity>?) {
        hideLoader()
        displayDataView(repositories)
        binding.recyclerView.scheduleLayoutAnimation()
    }

    private fun displayDataView(repositories: List<GithubEntity>?) {
        binding.viewEmpty.emptyContainer.visibility = View.GONE
        githubListAdapter.setItems(repositories)
    }

    private fun displayEmptyView() {
        hideLoader()
        binding.viewEmpty.emptyContainer.visibility = View.VISIBLE
    }

}
