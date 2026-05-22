package com.example.hr_app.presentation.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.presentation.components.EditVacancyBottomBarThree
import com.example.hr_app.presentation.components.Headers
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения экранов для редактирования вакансии
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun EditVacancy(modifier: Modifier = Modifier, navController: NavController) {
    var step by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            Headers(
                title = "Step ${step+1} of 3",
                iconFirst = R.drawable.close,
                tintFirst = Color(0xFF64748B),
                iconSecond = R.drawable.three_point,
                padding = PaddingValues(20.dp)
            )
        },
        bottomBar = {
            when (step) {
                1 -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                            .border(
                                1.dp,
                                Color(0xFFF1F5F9),
                                RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                            )
                            .padding(vertical = 15.5.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.save),
                                contentDescription = "",
                                tint = Color(0xFF737686)
                            )
                            Text(
                                text = "Save Draft",
                                color = Color(0xFF64748B),
                                style = CustomTheme.typography.FieldLabel14pxBold,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable(onClick = {
                                    logDebug(
                                        "CreateVacancyStep1",
                                        "Переход",
                                        "К следующему шагу создания вакансии"
                                    )
                                    step++
                                })
                                .background(Color(0xFFEFF6FF), RoundedCornerShape(12.dp))
                                .padding(vertical = 8.dp, horizontal = 40.dp)

                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.rigth),
                                contentDescription = "",
                                tint = Primary
                            )
                            Text(
                                text = "Continue",
                                color = Primary,
                                style = CustomTheme.typography.FieldLabel14pxBold,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                }
                else -> {
                    EditVacancyBottomBarThree(onClick = {
                        if (step == 0) step++
                        else if (step == 2) {
                            navController.navigate(VacanciesMain)
                        }
                    }, number = step)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            when (step) {
                0 -> EditVacancyStep1()
                1 -> EditVacancyStep2()
                2 -> EditVacancyStep3()
            }
        }
    }
}

@Preview
@Composable
private fun EditVacancyPrev() {
    CustomTheme {
        //EditVacancy()
    }
}