package com.pauln.userschallenge.ui.bindings

import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pauln.userschallenge.domain.User
import com.pauln.userschallenge.ui.userlist.UserListAdapter

class ViewBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("app:isLoading")
        fun isLoading(progressBar: ProgressBar, isLoading: Boolean) {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("app:users")
        fun setUsers(recyclerView: RecyclerView, users: List<User>?) {
            val adapter = recyclerView.adapter as UserListAdapter
            users?.let { adapter.setUsers(users) }
        }
    }
}