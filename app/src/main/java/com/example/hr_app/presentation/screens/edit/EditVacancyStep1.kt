package com.example.hr_app.presentation.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.components.ChipButton
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.helpers.Spacer8W
import com.example.hr_app.presentation.viewModel.GetInfoById
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения экрана пераого шага редактирования
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun EditVacancyStep1(
    modifier: Modifier = Modifier,
    viewModel: GetInfoById = hiltViewModel(),
    sharedPref: SharedPref = SharedPref(LocalContext.current),
) {
    var title by remember { mutableStateOf("") }
    var salaryRange by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    var depart by remember { mutableStateOf("") }
    var positions by remember { mutableStateOf("") }

    var experience by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var switch by remember { mutableStateOf(false) }
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
                text = "Edit Vacancy",
                color = Primary,
                style = CustomTheme.typography.ScreenHeader24px,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFD0E1FB), CircleShape)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Senior Product Designer",
                    color = Color(0xFF54647A),
                    style = CustomTheme.typography.BodySmall14px
                )
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        Pagination(currentPageIndex = 0)
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {

            val list = listOf(
                CreateVacancyStep(
                    title = "Vacancy Title",
                    value = title,
                    main = true,
                    placeholder = result?.title ?: "Senior Product Designer",
                    onValueChange = { title = it }
                ),
                CreateVacancyStep(
                    title = "Department",
                    value = depart,
                    main = false,
                    select = true,
                    placeholder = result?.department ?:"Product & Design",
                    onValueChange = { depart = it }
                ),
                CreateVacancyStep(
                    title = "Opening Date ",
                    value = title,
                    main = true,
                    select = true,
                    placeholder = result?.openingDate ?:"24.05.2024",
                    onValueChange = { depart = it }

                ),
                CreateVacancyStep(
                    title = "Position",
                    value = positions,
                    main = true,
                    select = true,
                    placeholder = result?.position ?:"Full-time",
                    onValueChange = { positions = it }

                ),
                CreateVacancyStep(
                    title = "City",
                    value = city,
                    main = false,
                    placeholder = result?.city ?:"New York, NY",
                    iconSecond = R.drawable.point_geo,
                    onValueChange = { city = it }
                ),

                )

            list.forEachIndexed { index, step ->
                Text(
                    text = step.title,
                    color = Text,
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.SemiBold
                )
                if (!step.select) {
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
        }
        Spacer24H()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White, RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Text(
                text = "Compensation & Requirements",
                color = Text,
                style = CustomTheme.typography.Subheader18px,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Salary Range",
                color = Color(0xFF434655),
                style = CustomTheme.typography.BodySmall14px,
                fontWeight = FontWeight.SemiBold
            )
            Input(
                value = salaryRange,
                onValueChange = { salaryRange = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = result?.salaryRange ?:"\$120k - \$160k",
                        color = Color(0xFF6B7280),
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.Normal
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.money_app),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )

            Text(
                text = "Required Quantity",
                color = Color(0xFF434655),
                style = CustomTheme.typography.BodySmall14px,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Input(
                    modifier= Modifier.fillMaxWidth(),
                    value = quantity,
                    onValueChange = { quantity = it },
                    placeholder = {
                        Text(
                            text = "1",
                            color = Color(0xFF6B7280),
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    },
                )
            }
            Text(
                text = "SExperience Level",
                color = Color(0xFF434655),
                style = CustomTheme.typography.BodySmall14px,
                fontWeight = FontWeight.SemiBold
            )
            val list = listOf(
                ExperienceLevelData(
                    title = "Entry Level",
                    enabled = false
                ),
                ExperienceLevelData(
                    title = "1-3 Years",
                    enabled = false
                ),
                ExperienceLevelData(
                    title = "3-5 Years",
                    enabled = false
                ),
                ExperienceLevelData(
                    title = "5+ Years",
                    enabled = true
                )
            )
            Row() {
                list.forEachIndexed { index, data ->
                    if (index != 3) {
                        ChipButton(checked = data.enabled, text = data.title, onClick = {})
                    }
                }
            }
            list.forEachIndexed { index, data ->
                if (index == 3) {
                    ChipButton(checked = data.enabled, text = data.title, onClick = {})
                }
            }

            Spacer24H()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFF6FF), RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFDBEAFE), RoundedCornerShape(12.dp))
                    .padding(24.dp)
            ) {
                Column() {
                    Row() {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon),
                            contentDescription = "",
                            tint = Primary
                        )
                        Text(
                            text = "Step 1: Core Details",
                            color = Color(0xFF1E3A8A),
                            style = CustomTheme.typography.FieldLabel14pxBold
                        )
                    }
                    Text(
                        text = "Fill in the basic information about the\n" +
                                "role. The next steps will cover the job\n" +
                                "description and candidate scorecard.",
                        color = Primary,
                        style = CustomTheme.typography.BodySmall14px
                    )
                }

            }
            Spacer24H()
        }
    }
}

data class ExperienceLevelData(
    val title: String,
    val enabled: Boolean,
    val imageVector: Int = R.drawable.close
)


@Preview
@Composable
private fun EditVacancyStep1Prev() {
    CustomTheme {
        EditVacancyStep1()
    }
}