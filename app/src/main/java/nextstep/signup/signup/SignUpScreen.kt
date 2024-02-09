package nextstep.signup.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.signup.component.SignUpHeader
import nextstep.signup.signup.component.SignUpInputTextField
import nextstep.signup.signup.component.SignUpSubmitButton

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.spacedBy(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var userNameTextState by remember { mutableStateOf("") }
        var emailTextState by remember { mutableStateOf("") }
        var passwordTextState by remember { mutableStateOf("") }
        var passwordConfirmTextState by remember { mutableStateOf("") }

        SignUpHeader()

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_username),
                value = userNameTextState,
                onTextChanged = { userNameTextState = it },
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_email),
                value = emailTextState,
                onTextChanged = { emailTextState = it },
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_password),
                value = passwordTextState,
                isInputPassword = true,
                onTextChanged = { passwordTextState = it },
            )

            SignUpInputTextField(
                labelName = stringResource(id = R.string.signup_input_text_label_password_confirm),
                value = passwordConfirmTextState,
                isInputPassword = true,
                onTextChanged = { passwordConfirmTextState = it },
            )
        }

        SignUpSubmitButton(
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen()
}
