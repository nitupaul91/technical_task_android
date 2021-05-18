package com.pauln.userschallenge

import com.pauln.userschallenge.domain.data.UsersRepository
import com.pauln.userschallenge.domain.usecases.AddUserUseCase
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AddUserUseCaseTest {

    @InjectMocks
    lateinit var addUserUseCase: AddUserUseCase

    @Mock
    lateinit var usersRepository: UsersRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addUser_returnsSuccess() {
        `when`(usersRepository.addUser("testName", "test@email.com"))
            .thenReturn(Completable.complete())

        addUserUseCase.addUser("testName", "test@email.com")
            .test()
            .assertComplete()
    }

    @Test
    fun addUser_returnsInvalidFieldsError() {
        `when`(usersRepository.addUser("testName", "testemail.com"))
            .thenReturn(Completable.error(Throwable("Please enter a correct name and email")))

        addUserUseCase.addUser("testName", "testemail.com")
            .test()
            .assertErrorMessage("Please enter a correct name and email")
    }
}
