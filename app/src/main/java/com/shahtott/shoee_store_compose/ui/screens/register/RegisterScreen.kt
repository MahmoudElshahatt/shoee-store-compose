package com.shahtott.shoee_store_compose.ui.screens.register

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shahtott.shoee_store_compose.R
import com.shahtott.shoee_store_compose.ui.screens.common.LightStatusBarIcons
import com.shahtott.shoee_store_compose.ui.screens.login.EmailTextField
import com.shahtott.shoee_store_compose.ui.screens.login.PasswordTextField
import com.shahtott.shoee_store_compose.ui.theme.TextGray


@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val uiState by viewModel.registerState
    RegisterContent(
        name = uiState.fullName,
        onNameChange = viewModel::onFullNameChange,
        email = uiState.email,
        onEmailChange = viewModel::onEmailChange,
        password = uiState.password,
        onPasswordChange = viewModel::onPasswordChange,
        confirmPassword = uiState.confirmPassword,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange
    )
}

@Composable
fun RegisterContent(
    name: String,
    onNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LightStatusBarIcons(false)

        Image(
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            painter = painterResource(R.drawable.ic_tinted_icon)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Let’s Get Started",
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Create an new account",
            color = TextGray,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(34.dp))

        NameTextField(Modifier.padding(horizontal = 12.dp), name, onNameChange)

        Spacer(modifier = Modifier.height(12.dp))

        EmailTextField(Modifier.padding(horizontal = 12.dp), email, onEmailChange)
        Spacer(modifier = Modifier.height(12.dp))
        PasswordTextField(
            Modifier.padding(horizontal = 12.dp),
            "Password",
            password,
            onPasswordChange
        )
        Spacer(modifier = Modifier.height(12.dp))
        PasswordTextField(
            Modifier.padding(horizontal = 12.dp),
            "Password Again",
            password,
            onPasswordChange
        )

    }
}

@Composable
fun NameTextField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = {
                Text(
                    "Full Name",
                    color = TextGray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF9AA0A6),
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    RegisterScreen()
}
