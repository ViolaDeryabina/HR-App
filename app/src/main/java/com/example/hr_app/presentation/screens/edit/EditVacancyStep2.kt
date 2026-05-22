package com.example.hr_app.presentation.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.screens.ChipButtonFill
import com.example.hr_app.presentation.viewModel.GetInfoById
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения экрана второго шага редактирования
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun EditVacancyStep2(
    modifier: Modifier = Modifier,
    viewModel: GetInfoById = hiltViewModel(),
    sharedPref: SharedPref = SharedPref(LocalContext.current),
) {
    var desc by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var term by remember { mutableStateOf("") }

    var testTask by remember { mutableStateOf("") }

    val scroll = rememberScrollState()
    val data by viewModel.data.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        val id = sharedPref.getVacancyId()
        val token = sharedPref.getToken()

        if (id != null && token != null) {
            viewModel.getInfoById(token, id)
        }
    }
    var result by remember { mutableStateOf<VacancyDto?>(null) }
    LaunchedEffect(data) {
        if (data is NetworkResult.Success) {
            result = (data as NetworkResult.Success).data

        }
    }
    Column(
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Content & Requirements",
                color = Primary,
                style = CustomTheme.typography.BodySmall14px,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "66% Complete",
                color = Color(0xFF54647A),
                style = CustomTheme.typography.BodyMedium16px,
                fontSize = 12.sp,
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Pagination(currentPageIndex = 0, progress = true)
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Job Details",
            color = Text,
            style = CustomTheme.typography.ScreenHeader24px,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Refine the core responsibilities and\n" +
                    "requirements for the Senior Product Designer\n" +
                    "role.",
            color = Secondary,
            style = CustomTheme.typography.BodyMedium16px,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(32.dp))
        val list = listOf(
            CreateVacancyStep(
                title = "Description & Responsibilities",
                value = desc,
                main = true,
                placeholder = "${result?.description}\n${result?.termsOfEmployments}",
                onValueChange = { desc = it }
            ),
            CreateVacancyStep(
                title = "Terms of Employment",
                value = term,
                main = false,
                select = true,
                placeholder = "Product & Design",
                onValueChange = { term = it }
            ),
            CreateVacancyStep(
                title = "Requirements",
                value = requirements,
                main = true,
                select = false,
                placeholder = "24.05.2024",
                onValueChange = { requirements = it }

            ),
            CreateVacancyStep(
                title = "Technical Questions",
                value = requirements,
                main = true,
                select = false,
                placeholder = "24.05.2024",
                onValueChange = { requirements = it },
                new = true

            ),
            CreateVacancyStep(
                title = "Test Task",
                value = testTask,
                main = true,
                select = true,
                placeholder = "Full-time",
                onValueChange = { testTask = it }

            )
        )

        list.forEachIndexed { index, step ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = step.title,
                    color = Text,
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.SemiBold
                )
                if (step.new) {
                    Row() {
                        val listItem = listOf(
                            ExperienceLevelData(
                                title = "Figma Proficiency",
                                enabled = true,
                                imageVector = R.drawable.close
                            ),
                            ExperienceLevelData(
                                title = "System Design",
                                enabled = true,
                                imageVector = R.drawable.close
                            ),
                        )
                        listItem.forEach { it ->
                            ChipButtonFill(
                                text = it.title,
                                checked = it.enabled,
                                onClick = { },
                                imageVector = it.imageVector,
                                iconSecond = true
                            )
                        }
                    }
                    ChipButtonFill(
                        text = "Add",
                        checked = false,
                        onClick = { },
                        imageVector = R.drawable.add,
                        iconFirst = true
                    )
                } else if (!step.select) {
                    Input(
                        value = step.value,
                        onValueChange = { step.onValueChange(it) },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = step.placeholder,
                                color = Color(0xFF6B7280),
                                style = CustomTheme.typography.BodyMedium16px,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        minLines = if (index == 0) 15 else 1,
                        leadingIcon = step.iconFirst?.let { iconRes ->
                            {
                                Icon(
                                    imageVector = ImageVector.vectorResource(iconRes),
                                    contentDescription = "",
                                    tint = Color(0xFF737686)
                                )
                            }
                        },
                        trailingIcon = step.iconSecond?.let { iconRes ->
                            {
                                Icon(
                                    imageVector = ImageVector.vectorResource(iconRes),
                                    contentDescription = "",
                                    tint = Color(0xFF737686)
                                )
                            }
                        }
                    )
                    Spacer8H()
                } else {
                    Select(
                        value = step.value,
                        onValueChange = { step.onValueChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = { step.onClick() }),
                        placeholder = {
                            Text(
                                text = step.placeholder,
                                color = Color(0xFF191B23),
                                style = CustomTheme.typography.BodyMedium16px,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        onClick = {

                        }
                    )
                    Spacer8H()
                }
            }
            Spacer16H()
        }
    }
    Spacer24H()

}

@Preview
@Composable
private fun EditVacancyStep2Prev() {
    CustomTheme {
        EditVacancyStep2()
    }
}