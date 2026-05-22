package com.example.hr_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.hr_app.R
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.White
// Назначение: Компонент для отображения bottom bar
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun EditVacancyBottomBar(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.left),
                contentDescription = "",
                tint = Color(0xFF737686)
            )
            Text(
                text = "BACK",
                color = Color(0xFF64748B),
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = "Save Draft",
            color = Secondary,
            style = CustomTheme.typography.BodyMedium16px,
            fontWeight = FontWeight.Normal
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable(onClick = {
                    logDebug(
                        "CreateVacancyStep1",
                        "Переход",
                        "К следующему шагу создания вакансии"
                    )
                    onClick()
                })
                .background(Color(0xFFEFF6FF), RoundedCornerShape(12.dp))
                .padding(vertical = 8.dp, horizontal = 32.dp)

        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.rigth),
                contentDescription = "",
                tint = Primary
            )
            Text(
                text = "NEXT",
                color = Primary,
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}





@Preview
@Composable
private fun EditVacancyBottomBarPrev() {
    CustomTheme {
        //EditVacancyBottomBar()
    }
}