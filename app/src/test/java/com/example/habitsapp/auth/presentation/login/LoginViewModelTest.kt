package com.example.habitsapp.auth.presentation.login

import com.example.habitsapp.auth.data.repository.FakeAuthentificationRepository
import com.example.habitsapp.auth.domain.matcher.EmailMatcher
import com.example.habitsapp.auth.domain.usecase.LoginUseCases
import com.example.habitsapp.auth.domain.usecase.LoginWithEmailUseCase
import com.example.habitsapp.auth.domain.usecase.ValidateEmailUseCase
import com.example.habitsapp.auth.domain.usecase.ValidatePasswordUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var authentificationRepository: FakeAuthentificationRepository


    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Before
    fun setUp() {
        authentificationRepository = FakeAuthentificationRepository()
        val usecases = LoginUseCases(
            loginUseCase = LoginWithEmailUseCase(authentificationRepository),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(object : EmailMatcher {
                override fun isValid(email: String): Boolean {
                    return email.isNotEmpty()
                }
            })

        )

        loginViewModel = LoginViewModel(usecases)
    }

    @Test
    fun `GIVEN initial state WHEN ViewModel is created THEN state is empty`() {
        val state = loginViewModel.state
        assertEquals(
            LoginState(
                email = "",
                password = "",
                emailError = null,
                passwordError = null,
                isLoggedIn = false,
                isLoading = false
            ),
            state
        )
    }


    @Test
    fun `GIVEN invalid email WHEN email error THEN show email error` (){
        loginViewModel.onEvent(LoginEvent.EmailChange(""))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.emailError)

    }


    @Test
    fun `GIVEN an email  WHEN verify the state THEN update the email`(){
        val initialState = loginViewModel.state.email
        assertEquals(initialState,"" )
        loginViewModel.onEvent(LoginEvent.EmailChange("asd@asd.com"))
        val updatedState= loginViewModel.state.email
        assertEquals(
            "asd@asd.com",
            updatedState
        )

    }


    @Test
    fun `GIVEN invalid email WHEN login is triggered THEN show email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange(""))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.emailError)
    }

    @Test
    fun `GIVEN valid email WHEN login is triggered THEN no email error`() {
        loginViewModel.onEvent(LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assert(state.emailError == null)
    }

    @Test
    fun `GIVEN invalid password WHEN login is triggered THEN show password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asd"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNotNull(state.passwordError)
    }

    @Test
    fun `GIVEN valid password WHEN login is triggered THEN no password error`() {
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)
        val state = loginViewModel.state
        assertNull(state.passwordError)
    }

    @Test
    fun `GIVEN valid credentials WHEN login is triggered THEN starts loading and logs in`() = scope.runTest {
        loginViewModel.onEvent(LoginEvent.EmailChange("whatever"))
        loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
        loginViewModel.onEvent(LoginEvent.Login)
        var state = loginViewModel.state
        assertNull(state.passwordError)
        assertNull(state.emailError)
        assert(state.isLoading)
        advanceUntilIdle()
        state = loginViewModel.state
        assert(state.isLoggedIn)
    }

    @Test
    fun `GIVEN valid credentials but server error WHEN login is triggered THEN show error and stop loading`() =
        scope.runTest {
            authentificationRepository.fakeError = true
            loginViewModel.onEvent(LoginEvent.EmailChange("whatever"))
            loginViewModel.onEvent(LoginEvent.PasswordChange("asdASD123"))
            loginViewModel.onEvent(LoginEvent.Login)
            var state = loginViewModel.state
            assertNull(state.passwordError)
            assertNull(state.emailError)
            assert(state.isLoading)
            advanceUntilIdle()
            state = loginViewModel.state
            assert(!state.isLoggedIn)
            assertNotNull(state.emailError)
            assert(!state.isLoading)
        }

}