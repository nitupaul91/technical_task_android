package com.pauln.userschallenge.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pauln.userschallenge.databinding.UserListItemBinding
import com.pauln.userschallenge.domain.User

class UserListAdapter(
    val onUserLongPressed: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val users: MutableList<User> = mutableListOf()

    fun setUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(users[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = DataBindingUtil.getBinding<UserListItemBinding>(itemView)

        fun bindView(user: User) {
            binding?.itemCl?.setOnLongClickListener {
                onUserLongPressed.invoke(user)
                return@setOnLongClickListener true
            }

            binding?.user = user
        }
    }
}