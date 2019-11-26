package com.example.matictest.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.matictest.R
import com.example.matictest.data.local.entity.GithubEntity
import com.example.matictest.databinding.RepoListItemBinding
import com.example.matictest.ui.extensions.withNotNullNorEmpty
import com.example.matictest.utils.AppUtils
import com.squareup.picasso.Picasso

class GithubListAdapter(private val context: Context) : RecyclerView.Adapter<GithubListAdapter.CustomViewHolder>() {
    private var items: MutableList<GithubEntity> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemBinding = RepoListItemBinding.inflate(layoutInflater, parent, false)
        val customItemViewHolder = CustomViewHolder(itemBinding)

        return customItemViewHolder
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    fun setItems(data: List<GithubEntity>?) {
        data?.withNotNullNorEmpty {
            items.addAll(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun getItem(position: Int): GithubEntity {
        return items[position]
    }


    inner class CustomViewHolder(private val binding: RepoListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(githubEntity: GithubEntity) {
            Picasso.get().load(githubEntity.owner.avatarUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(binding.itemProfileImg)

            binding.itemTitle.text = githubEntity.fullName
            binding.itemTime.text = String.format(context.getString(R.string.item_date),
                    AppUtils.getDate(githubEntity.createdAt),
                    AppUtils.getTime(githubEntity.createdAt))

            binding.itemDesc.text = githubEntity.description


            binding.likesCount.text = AppUtils.formatLikesNumber(githubEntity.starsCount)

        }

    }
}
