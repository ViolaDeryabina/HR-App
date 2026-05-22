package com.example.hr_app.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

@Composable
fun ChipButton(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    text: String,
    onClick: () -> Unit = {}
) {
    val colorBorder = if (checked) Primary else Color(0xFFC3C6D7)
    val colorText = if (checked) Color(0xFF004AC6) else Color(0xFF434655)
    val colorBack = if (checked) Color(0xFF2563EB).copy(0.1f) else White

    Button(
        onClick = { onClick() },
        colors = ButtonColors(
            containerColor = colorBack,
            contentColor = colorText,
            disabledContentColor = colorText,
            disabledContainerColor = colorBack
        ),
        modifier = modifier.border(if (checked) 2.dp else 1.dp, colorBorder, CircleShape)
    ) {
        Text(
            text = text,
            color = colorText,
            style = if (checked) CustomTheme.typography.FieldLabel14pxBold else CustomTheme.typography.BodyMedium16px,
            fontSize = 12.sp
        )
    }
}



@Preview
@Composable
private fun ChipButtonPrev() {
    CustomTheme {
        // ChipButton()
    }
}