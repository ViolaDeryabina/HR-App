package com.example.hr_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.navigation.Authorization
import com.example.hr_app.presentation.navigation.PostLoginLoading
import com.example.hr_app.presentation.viewModel.RegisterViewModel
import com.example.hr_network_library.common.logDebug
import com.example.hr_network_library.common.logError
import com.example.hr_network_library.data.dto.RegisterRequest
import com.example.hr_uikit.presentation.components.CustomCheckBox
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Создание экрана для регистрации пользователя
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
@Composable
fun Registration(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    navController: NavController,

) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var check by rememberSaveable { mutableStateOf(false) }
    var enabledButton by rememberSaveable { mutableStateOf(false) }

    var showEyePassword by rememberSaveable { mutableStateOf(false) }
    var showEyeConfirmPassword by rememberSaveable { mutableStateOf(false) }

    val data by viewModel.data.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    val scroll = rememberScrollState()


    if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
        if (password == confirmPassword ) {
            enabledButton = true
        } else {
            enabledButton = false
        }
    }
    val color =
        if (password != confirmPassword && confirmPassword.isNotEmpty() && password.isNotEmpty()) Error else Color(
            0xFF6B7280
        )

    LaunchedEffect(data) {
        if (data != null) {
            navController.navigate(Authorization)
        }
    }
    LaunchedEffect(error) {
        if (error != null) {
            logError("Registration", "Ошибка", "${error}")
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFFAF8FF)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .background(
                    White, RoundedCornerShape(12.dp)
                )
                .padding(24.dp).verticalScroll(scroll),

            ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "HR Connect",
                style = CustomTheme.typography.ScreenHeader24px,
                color = Primary
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Modern Talent Engine for HR Excellence",
                style = CustomTheme.typography.BodySmall14px,
                color = Secondary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "First Name",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Text
                    )
                    Input(
                        modifier = Modifier,
                        value = firstName,
                        onValueChange = { firstName = it },
                        placeholder = {
                            Text(
                                "John",
                                style = CustomTheme.typography.BodySmall14px,
                                color = Secondary
                            )
                        }
                    )


                }
                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Last Name",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Text
                    )

                    Input(
                        modifier = Modifier,
                        value = lastName,
                        onValueChange = { lastName = it },
                        placeholder = {
                            Text(
                                "Doe",
                                style = CustomTheme.typography.BodySmall14px,
                                color = Secondary
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Email",
                style = CustomTheme.typography.BodySmall14px,
                color = Text
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        "name@domain.ru",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Secondary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.email),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )
            Text(
                "Lowercase letters and digits only (name@domain.ru)",
                style = CustomTheme.typography.BodySmall14px,
                fontSize = 12.sp,
                color = Secondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            //password
            Text(
                "Password",
                style = CustomTheme.typography.BodySmall14px,
                color = Text
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        "••••••••",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Secondary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.key),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = {
                            showEyePassword = !showEyePassword
                        }),
                        imageVector = ImageVector.vectorResource(R.drawable.eye_open),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                },
                visualTransformation = if (!showEyePassword) PasswordVisualTransformation() else VisualTransformation.None


            )
            Text(
                "Minimum 8 characters",
                style = CustomTheme.typography.BodySmall14px,
                color = Text
            )
            Spacer(modifier = Modifier.height(16.dp))
            //confirm password
            Text(
                "Confirm Password",
                style = CustomTheme.typography.BodySmall14px,
                color = Text
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = {
                    Text(
                        "••••••••",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Secondary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.key),
                        contentDescription = "",
                        tint = color
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(onClick = {
                            showEyeConfirmPassword = !showEyeConfirmPassword
                        }),
                        imageVector = ImageVector.vectorResource(R.drawable.eye_open),
                        contentDescription = "",
                        tint = color
                    )
                },
                visualTransformation = if (!showEyeConfirmPassword) PasswordVisualTransformation() else VisualTransformation.None,
                isError = color == Error


            )
            if (color == Error) {
                Text(
                    "Passwords do not match",
                    style = CustomTheme.typography.BodySmall14px,
                    color = color
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-10).dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                CustomCheckBox(checked = check, onCheckedChange = { check = !check }, enabled = true)
                Text(
                    "I agree to the terms of use and privacy policy.",
                    modifier = Modifier.fillMaxWidth(),
                    style = CustomTheme.typography.BodySmall14px,
                    color = Color(0xFF434655)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                onClick = {
                    viewModel.register(
                        RegisterRequest(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            password = password,
                            passwordConfirm = confirmPassword
                        )
                    )
                },
                text = "Register",
                enabled = enabledButton,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account?",
                    style = CustomTheme.typography.BodySmall14px,
                    color = Secondary
                )
                TextButton(onClick = {
                    logDebug("Registration", "Переход", "Форма авторизации")
                    navController.navigate(Authorization)
                }) {
                    Text(
                        "Sign in",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RegistrationPrev() {
    CustomTheme {
        //Registration()
    }
}