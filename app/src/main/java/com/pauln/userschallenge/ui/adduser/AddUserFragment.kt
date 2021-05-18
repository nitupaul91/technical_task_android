package com.pauln.userschallenge.ui.adduser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.pauln.userschallenge.R
import com.pauln.userschallenge.databinding.FragmentAddUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : DialogFragment() {

    private val addUserViewModel: AddUserViewModel by viewModels()
    private lateinit var binding: FragmentAddUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = addUserViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addUserViewModel.newUserAddedEvent.observe(viewLifecycleOwner) {
            showAddUserSuccessDialog()
        }

        addUserViewModel.invalidFieldsEvent.observe(viewLifecycleOwner) {
            showInvalidFieldsSnackbar()
        }
    }

    private fun showAddUserSuccessDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.alert_dialog_added_user_success)
            .setNeutralButton(R.string.alert_dialog_ok) { dialog, _ ->
                dialog.dismiss()
                this.dismiss()
            }
            .show()
    }

    private fun showInvalidFieldsSnackbar() {
        Snackbar.make(requireView(), R.string.alert_dialog_invalid_email, Snackbar.LENGTH_LONG)
            .show()
    }
}
