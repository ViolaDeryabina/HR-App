package com.example.hr_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.White

// Назначение: Создание экрана для регистрации пользователя
// Автор: Дерябина В.Н.
// Дата создания 20-05-2026
@Composable
fun PostLoginLoading(modifier: Modifier = Modifier) {
    Box(modifier= Modifier.fillMaxSize().background(White)) { }
}

@Preview
@Composable
private fun PostLoginLoadingPrev() {
    CustomTheme{
        PostLoginLoading()
    }
}