package com.pauln.userschallenge.ui.adduser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pauln.userschallenge.domain.usecases.AddUserUseCase
import com.pauln.userschallenge.ui.userlist.TAG
import com.pauln.userschallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val newUserAddedEvent = SingleLiveEvent<Any>()
    val invalidFieldsEvent = SingleLiveEvent<Any>()
    val userEmail = MutableLiveData<String>()
    val userName = MutableLiveData<String>()

    fun addUser() {
        disposable.add(
            addUserUseCase.addUser(userName.value, userEmail.value)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    newUserAddedEvent.call()
                }, {
                    invalidFieldsEvent.call()
                    Log.d(TAG, it.message ?: "Fallback error message.")
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
