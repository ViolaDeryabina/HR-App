package com.example.hr_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.White

// Назначение: Компонент для фильтров
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun ActiveFiltersItem(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
    paddingValues: PaddingValues
) {
    Row(
        modifier = modifier
            .background(Color(0xFFD0E1FB), CircleShape)
            .border(1.dp,Color(0xFFC3C6D7), CircleShape)
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview
@Composable
private fun ActiveFiltersItemPrev() {
    CustomTheme {
        //ActiveFiltersItem()
    }
}