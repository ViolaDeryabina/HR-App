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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.navigation.Authorization
import com.example.hr_app.presentation.navigation.PostLoginLoading
import com.example.hr_app.presentation.navigation.Registration
import com.example.hr_app.presentation.viewModel.AuthViewModel
import com.example.hr_network_library.common.logDebug
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.AuthRequest
import com.example.hr_uikit.presentation.components.CustomCheckBox
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Tertiary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Создание экрана для входа пользователя
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
@Composable
fun Authorization(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController,
    sharedPref: SharedPref
) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }

    val data by authViewModel.data.collectAsStateWithLifecycle()
    LaunchedEffect(data) {
        if (data is NetworkResult.Success) {
            val response = (data as NetworkResult.Success).data
            val token = response.token
            sharedPref.saveToken(token)
            navController.navigate(PostLoginLoading)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Primary.copy(0.05f),
                        Tertiary.copy(0.05f)
                    )
                )
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(White, RoundedCornerShape(12.dp))
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.hr),
                    contentDescription = "",
                    tint = Primary
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "HR Connect",
                style = CustomTheme.typography.ScreenHeader24px,
                color = Text

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Talent Management Portal",
                style = CustomTheme.typography.BodySmall14px,
                color = Secondary
            )
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(5.dp, RoundedCornerShape(12.dp))
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(25.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Login",
                    modifier = Modifier.fillMaxWidth(),
                    style = CustomTheme.typography.BodySmall14px,
                    color = Color(0xFF434655)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Input(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = login,
                    onValueChange = { login = it },
                    placeholder = {
                        Text(
                            "username or email",
                            style = CustomTheme.typography.BodySmall14px,
                            color = Secondary
                        )
                    }
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Password",
                    modifier = Modifier.fillMaxWidth(),
                    style = CustomTheme.typography.BodySmall14px,
                    color = Color(0xFF434655)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Input(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            "**********",
                            style = CustomTheme.typography.BodySmall14px,
                            color = Secondary
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.eye_open),
                            contentDescription = "",
                            tint = Secondary
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = (-10).dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomCheckBox(
                        checked = check,
                        onCheckedChange = { check = !check },
                        enabled = true
                    )
                    Text(
                        "Remember Me",
                        modifier = Modifier.fillMaxWidth(),
                        style = CustomTheme.typography.BodySmall14px,
                        color = Color(0xFF434655)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                PrimaryButton(
                    onClick = {
                        logDebug("", "", "")
                        authViewModel.auth(
                            AuthRequest(
                                login, password
                            )
                        )
                    },
                    text = "Login",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                    enabled = true
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextButton(onClick = {
                    logDebug("Authorization", "Переход", "На  форму регистрации")
                    navController.navigate(Registration)
                }) {
                    Text(
                        "Sign in",
                        style = CustomTheme.typography.BodySmall14px,
                        color = Primary
                    )
                }
            }
            TextButton(onClick = {}) {
                Text(
                    "Forgot Password",
                    style = CustomTheme.typography.BodySmall14px,
                    color = Primary
                )
            }
        }
    }

}

@Preview
@Composable
private fun AuthorizationPrev() {
    CustomTheme {
        //Authorization()
    }
}