package com.example.hr_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White
import io.appmetrica.analytics.impl.Wh
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Назначение: Создание экрана для регистрации пользователя
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
@Composable
fun PostLoginLoading(modifier: Modifier = Modifier, navController: NavController) {
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        loadProgress { it->
            currentProgress = it
        }
        loading = false
        navController.navigate(VacanciesMain)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFFAF8FF)
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Box(
                modifier = Modifier
                    .background(Primary, RoundedCornerShape(12.dp))
                    .size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.hr),
                    contentDescription = "",
                    tint = White
                )
            }
            Spacer(modifier= Modifier.height(32.dp))
            if (loading) {
                LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    color = Primary
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
                "Setting up your workspace...",
                style = CustomTheme.typography.BodyMedium16px,
                color = Secondary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                "Synchronizing talent data and active vacancies",
                style = CustomTheme.typography.BodySmall14px,
                color = Color(0xff737686)
            )
        }
    }
}

suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..20) {
        updateProgress(i.toFloat() / 20)
        delay(20)
    }
}


@Preview
@Composable
private fun PostLoginLoadingPrev() {
    CustomTheme {
        //PostLoginLoading()
    }
}