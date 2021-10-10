package com.indexer.bilemo.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indexer.bilemo.R
import com.indexer.bilemo.databinding.UserItemBinding
import com.indexer.bilemo.user.enitity.UserResponse

class UserListAdapter(private val onClick: (UserResponse) -> Unit) :
    ListAdapter<UserResponse, UserListAdapter.UserViewHolder>(UserDiffCallback) {

    class UserViewHolder(itemView: View, val onClick: (UserResponse) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var currentUserName: UserResponse? = null
        private val binding = UserItemBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                currentUserName?.let { onClick(it) }
            }
        }
        fun bind(user: UserResponse) {
            currentUserName = user
            setUserPersonalInformation(user, binding)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)
    }
}

fun setUserPersonalInformation(it: UserResponse, binding: UserItemBinding) {
    val context = binding.root.context
    val stringBuilder = StringBuilder()
    stringBuilder.append(context.getString(R.string.user_name)).append(it.username)
    binding.userName.text = stringBuilder.toString()
    val addressStringBuilder = StringBuilder()
    val addressValue =
        it.address.suite + " , " + it.address.street + " , " + it.address.city + " , " + it.address.zipcode
    binding.userAddress.text =
        addressStringBuilder.append(binding.userAddress.context.getString(R.string.address))
            .append(addressValue)
    val websiteStringBuilder = StringBuilder()

    binding.userWebsite.text =
        websiteStringBuilder.append(context.getString(R.string.website)).append(it.website)
    val emailStringBuilder = StringBuilder()
    binding.userEmail.text =
        emailStringBuilder.append(context.getString(R.string.email)).append(it.email)
}

object UserDiffCallback : DiffUtil.ItemCallback<UserResponse>() {
    override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
        return oldItem.id == newItem.id
    }
}