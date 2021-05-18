package com.pauln.userschallenge.ui.userlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pauln.userschallenge.domain.User
import com.pauln.userschallenge.domain.usecases.DeleteUserUseCase
import com.pauln.userschallenge.domain.usecases.GetLastPageUsersUseCase
import com.pauln.userschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val TAG = "UserListViewModel"

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getLastPageUsersUseCase: GetLastPageUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    val isLoading = MutableLiveData<Boolean>()
    val userList = MutableLiveData<List<User>>()
    val openAddUserEvent = SingleLiveEvent<Any>()

    init {
        getLastPageUsers()
        shouldUpdateUserList()
    }

    private fun shouldUpdateUserList() {
        disposable.add(
            getLastPageUsersUseCase.shouldUpdateUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it) {
                        getLastPageUsers()
                    }
                }, {
                    Log.d(TAG, it.message ?: "Fallback error message.")
                })
        )
    }

    private fun getLastPageUsers() {
        isLoading.value = true

        disposable.add(
            getLastPageUsersUseCase.get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doAfterTerminate { isLoading.value = false }
                .subscribe({ users ->
                    userList.value = users
                }) {
                    Log.d(TAG, it.message ?: "Fallback error message.")
                }
        )
    }

    fun onAddUserClicked() {
        openAddUserEvent.call()
    }

    fun deleteUser(userId: Long) {
        disposable.add(
            deleteUserUseCase.delete(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(::getLastPageUsers) {
                    Log.d(TAG, it.message ?: "Fallback error message.")
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
