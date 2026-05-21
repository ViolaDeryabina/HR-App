package com.example.hr_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

@Composable
fun Headers(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    title: String,
    iconFirst: Int? = null,
    tintFirst: Color = Secondary,
    iconSecond: Int,
    tintSecond: Color = Color(0xFF64748B),
    onClickFirst: () -> Unit = {},
    onClickSecond: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .border(1.dp, Color(0xFFE2E8F0))
            .padding(padding),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconFirst != null) {
            Icon(
                imageVector = ImageVector.vectorResource(iconFirst),
                contentDescription = "",
                tint = tintFirst,
                modifier = Modifier.clickable(onClick = { onClickFirst() })
            )
        }
        Text(
            title,
            style = CustomTheme.typography.FieldLabel14pxBold,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Text
        )
        Icon(
            imageVector = ImageVector.vectorResource(iconSecond),
            contentDescription = "",
            tint = tintSecond,
            modifier = Modifier.clickable(onClick = { onClickSecond() })
        )
    }
}

@Preview
@Composable
private fun HeadersPrev() {
    CustomTheme {
        // Headers()
    }
}