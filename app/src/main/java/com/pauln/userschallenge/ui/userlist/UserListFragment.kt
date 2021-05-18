package com.pauln.userschallenge.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.pauln.userschallenge.R
import com.pauln.userschallenge.databinding.FragmentUserListBinding
import com.pauln.userschallenge.domain.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val usersViewModel: UserListViewModel by viewModels()
    private lateinit var binding: FragmentUserListBinding

    private val usersAdapter: UserListAdapter = UserListAdapter(::onUserLongPressed)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserListBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = usersViewModel
        }

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.usersRv.adapter = usersAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersViewModel.openAddUserEvent.observe(viewLifecycleOwner, {
            navigateToAddUser()
        })
    }

    private fun navigateToAddUser() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_userListFragment_to_addUserFragment)
    }

    private fun onUserLongPressed(user: User) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.alert_dialog_title_delete)
            .setPositiveButton(R.string.alert_dialog_delete_positive) { dialog, _ ->
                dialog.dismiss()
                usersViewModel.deleteUser(userId = user.id)
            }
            .setNegativeButton(R.string.alert_dialog_delete_negative) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
