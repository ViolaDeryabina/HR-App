package com.example.hr_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White
// Назначение: Компонент для отображения карточки
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun CardMini(modifier: Modifier = Modifier, title: String, desc: String) {
    Column(modifier = modifier
        .background(White, RoundedCornerShape(12.dp))
        .padding(16.dp)) {
        Text(
            text = title,
            color = Secondary,
            style = CustomTheme.typography.BodySmall14px,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = desc,
            color = Text,
            style = CustomTheme.typography.Subheader18px,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun CardMiniPrev() {
    CustomTheme {
        //CardMini()
    }
}