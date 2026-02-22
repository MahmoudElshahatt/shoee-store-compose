package com.shahtott.shoee_store_compose.ui.screens.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shahtott.shoee_store_compose.R
import com.shahtott.shoee_store_compose.ui.common.PrimaryButton
import com.shahtott.shoee_store_compose.ui.theme.GrayBorder
import com.shahtott.shoee_store_compose.ui.theme.TextGray

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    val uiState by viewModel.loginState
    LoginContent(
        email = uiState.email,
        onEmailChange = viewModel::onEmailChange,
        password = uiState.password,
        onPasswordChange = viewModel::onPasswordChange,
        onRegisterClicked = onRegisterClicked,
        onLoginClicked = onLoginClicked
    )
}

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(80.dp))
        Image(
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            painter = painterResource(R.drawable.ic_tinted_icon)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Welcome to Shoee Store",
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Sign in to continue",
            color = TextGray,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(34.dp))
        EmailTextField(Modifier.padding(horizontal = 12.dp), email, onEmailChange)
        Spacer(modifier = Modifier.height(14.dp))
        PasswordTextField(
            Modifier.padding(horizontal = 12.dp),
            "Your Password",
            password,
            onPasswordChange
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryButton(text = "Sign In") {
            onLoginClicked()
        }

        Spacer(modifier = Modifier.height(26.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            OrDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                textColor = TextGray
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        SocialLoginButton(
            text = "Login with Google",
            iconRes = R.drawable.ic_google_logo,
            onClick = { /* Google sign-in */ }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SocialLoginButton(
            text = "Login with Facebook",
            iconRes = R.drawable.ic_facebook,
            onClick = { /* Facebook sign-in */ }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Forgot Password?",
            modifier = Modifier.clickable { /* Forgot password action */ },
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Text(
                text = "Don’t have a account?",
                color = TextGray,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                )
            )
            Text(
                text = "Register",
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onRegisterClicked() },
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}

@Composable
fun EmailTextField(
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
                    "Your Email",
                    color = TextGray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFF9AA0A6),
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
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

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = {
                Text(
                    text,
                    color = TextGray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFF9AA0A6),
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
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
                .height(56.dp),
//            trailingIcon = {
//                if (showPassword) {
//                    IconButton(onClick = { showPassword = false }) {
//                        Icon(
//                            imageVector = Icons.Default.s,
//                            contentDescription = null
//                        )
//                    }
//                } else {
//                    IconButton(onClick = { showPassword = true }) {
//                        Icon(
//                            imageVector = Icons.Filled.Lock,
//                            contentDescription = null
//                        )
//                    }
//                }
            //  }
        )
    }
}

@Composable
fun OrDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
    textColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = 1.dp
        )

        Text(
            text = " OR ",
            modifier = Modifier.padding(horizontal = 24.dp),
            style = MaterialTheme.typography.labelLarge,
            color = textColor,
            fontWeight = FontWeight.Medium
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = color,
            thickness = 1.dp
        )
    }
}

@Composable
fun SocialLoginButton(
    text: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = ButtonDefaults.outlinedButtonColors().copy(
            containerColor = Color.White,
        ),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(
            width = 1.dp,
            color = GrayBorder
        ),
        onClick = onClick,
    ) {
        Row(Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 8.dp)
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = text,
                color = TextGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClicked = {}, onRegisterClicked = {})
}