package com.example.hr_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

// Назначение: Компонент для отображения CandidateStatus
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun CandidateStatusItem(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
    paddingValues: PaddingValues,
    check: Boolean
) {
    Row(
        modifier = modifier
            .background(if (check) Primary.copy(0.05f) else White, RoundedCornerShape(12.dp))
            .border(1.dp, if (check) Primary else Color(0xFFC3C6D7), RoundedCornerShape(12.dp))
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview
@Composable
private fun CandidateStatusItemPrev() {
    CustomTheme {
        //CandidateStatusItem()
    }
}