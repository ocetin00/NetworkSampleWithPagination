package com.oguzhancetin.networksample.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oguzhancetin.networksample.data.domain.AppUser
import com.oguzhancetin.networksample.databinding.UserCardBinding


class RcUserListAdapter :
    PagingDataAdapter<AppUser, RecyclerView.ViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }



    companion object DiffCallback : DiffUtil.ItemCallback<AppUser>() {
        override fun areItemsTheSame(oldItem: AppUser, newItem: AppUser): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: AppUser, newItem: AppUser): Boolean {
            return oldItem == newItem
        }
    }

    class UserViewHolder(private val binding: UserCardBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(user: AppUser){
            binding.textViewUserName.text = user.userName
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       getItem(position)?.let { (holder as UserViewHolder).bind(it) }
    }
}