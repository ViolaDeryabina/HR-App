package com.example.hr_app.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

// Назначение: Компонент для отображения кнопки
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun ChipButtonFill(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    text: String,
    onClick: () -> Unit = {},
    imageVector: Int,
    iconFirst: Boolean = false,
    iconSecond: Boolean = false
) {
    val colorBorder = if (checked) Primary else Color(0xFFC3C6D7)
    val colorText = if (checked) White else Color(0xFF434655)
    val colorIcon = if (checked) White else Color(0xFF434655)
    val colorBack= if (checked) Primary else Color(0xFFE1E2ED)


    Button(
        onClick = { onClick() },
        shape = CircleShape,
        colors = ButtonColors(
            containerColor = colorBack,
            contentColor = White,
            disabledContentColor = White,
            disabledContainerColor = colorBack
        ),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (iconFirst) {
                Icon(
                    imageVector = ImageVector.vectorResource(imageVector),
                    contentDescription = "",
                    tint = colorIcon
                )
            }
            Text(
                text = text,
                color = colorText,
                style = if (checked) CustomTheme.typography.FieldLabel14pxBold else CustomTheme.typography.BodyMedium16px,
                fontSize = 12.sp
            )
            if (iconSecond) {
                Icon(
                    imageVector = ImageVector.vectorResource(imageVector),
                    contentDescription = "",
                    tint = colorIcon

                )
            }
        }

    }
}