package com.example.hr_app.presentation.screens.create

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.R
import com.example.hr_app.data.ResultStep1
import com.example.hr_app.data.ResultStep2
import com.example.hr_app.data.ResultStep3
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.components.Headers
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_app.presentation.viewModel.CreateVacancyViewModel
import com.example.hr_app.presentation.viewModel.GetDepartmentsViewModel
import com.example.hr_app.presentation.viewModel.GetPositionsViewModel
import com.example.hr_network_library.common.logDebug
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения экранов для добавления вакансий
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun CreateVacancy(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModelDepartments: GetDepartmentsViewModel,
    viewModelPositions: GetPositionsViewModel,
    viewModel: CreateVacancyViewModel,
    sharedPref: SharedPref
) {
    var step by remember { mutableIntStateOf(0) }
    var token by remember { mutableStateOf("") }
    var step1 by remember { mutableStateOf<ResultStep1?>(null) }
    var step2 by remember { mutableStateOf<ResultStep2?>(null) }
    var step3 by remember { mutableStateOf<ResultStep3?>(null) }

    Scaffold(
        topBar = {
            Headers(
                title = "Create Vacancy",
                iconFirst = R.drawable.close,
                tintFirst = Color(0xFF64748B),
                iconSecond = R.drawable.three_point,
                padding = PaddingValues(20.dp)
            )
        },
        bottomBar = {
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
                            if (step < 2) {
                                step += 1
                            } else {
                                val finalStep1 = sharedPref.getResultStep1()
                                val finalStep2 = sharedPref.getResultStep2()
                                val finalStep3 = sharedPref.getResultStep3()

                                viewModel.createVacancy(
                                    token, VacancyDto(
                                        city = finalStep1?.city ?: "",
                                        created = "",
                                        department = finalStep1?.depart ?: "",
                                        description = finalStep2?.description ?: "",
                                        email = finalStep3?.email ?: "",
                                        employee = finalStep1?.city ?: "",
                                        experience = finalStep1?.experience ?: "",
                                        openingDate = "",
                                        phone = finalStep3?.phone ?: "",
                                        position = finalStep1?.positions ?: "",
                                        public = finalStep1?.public ?: false,
                                        quantity = finalStep1?.quantity ?: 0,
                                        requirements = "",
                                        responsiblePerson = "e4xaa41jihyipvz",
                                        salaryRange = finalStep1?.salaryRange ?: "",
                                        supportDocuments = listOf(),
                                        technicalQuestions = "",
                                        termsOfEmployments = "",
                                        testTask = finalStep2?.testTask ?: "",
                                        title = finalStep1?.title ?: "",
                                        updated = ""
                                    )
                                )
                                logDebug("TEST", "", "")
                                // Безопасный переход на финальный экран без зацикливания
                                navController.navigate(VacanciesMain) {
                                    popUpTo(VacanciesMain) { inclusive = true }
                                }
                            }
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            when (step) {
                0 -> {
                    CreateVacancyStep1(
                        navController = navController,
                        viewModelDepartments = viewModelDepartments,
                        viewModelPositions = viewModelPositions,
                        sharedPref = sharedPref
                    )
                }

                1 -> {
                    CreateVacancyStep2(
                        navController = navController,
                        sharedPref = sharedPref
                    )
                }

                2 -> {
                    CreateVacancyStep3(
                        sharedPref = sharedPref
                    )
                }
            }
        }
    }
}