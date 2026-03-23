// kotlin
package com.example.authentication_presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_presentation.HabitButton
import com.example.core_presentation.HabitPasswordTextfield
import com.example.core_presentation.HabitTextfield
import com.example.authentication_presentation.login.LoginEvent
import com.example.authentication_presentation.login.LoginState


@Composable
fun LoginForm(
  state: LoginState,
  onEvent: (LoginEvent) -> Unit,
  onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerModifier = modifier
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(20.dp))
    val focusManager= LocalFocusManager.current

    Box(modifier = modifier
        .fillMaxWidth()
        .background(Color.White, shape = RoundedCornerShape(20.dp))
        .padding(16.dp),
        contentAlignment = Alignment.Center) {
        if (state.isLoading){
            CircularProgressIndicator()
        }
        Column(
            modifier = containerModifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log in with Email",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.background
            )

            val fieldModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 6.dp)

            HabitTextfield(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.EmailChange(it))},
                contentDescription = "Enter email",
                placeholder = "Email",
                modifier = fieldModifier,
                leadingIcon = Icons.Outlined.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    //this is to move the focus to next text field when user clicks next in keyboard
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                errorMessage = state.emailError,
                isEnabled = !state.isLoading
            )

            HabitPasswordTextfield(
                value = state.password,
                placeholder = "Password",
                onValueChange = { onEvent(LoginEvent.PasswordChange(it))},
                contentDescription = "Enter password",
                modifier = fieldModifier,
                errorMessage = state.passwordError,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onAny = {
                    //validate and login when user clicks done in keyboard
                    focusManager.clearFocus()
                    onEvent(LoginEvent.Login)
                })
            )

            HabitButton(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                isEnabled = !state.isLoading,
                onClick = {onEvent(LoginEvent.Login)}
            )

            TextButton(onClick = { }) {
                Text(
                    "Forgot password?",
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }

            TextButton(onClick =  onSignUp) {
                Text(
                    buildAnnotatedString {
                        append("Don't have an account?")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" Sign up")
                        }
                    },
                    color = MaterialTheme.colorScheme.tertiary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        if (state.isLoading){
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    LoginForm(LoginState(), onEvent = {}, {})
}
