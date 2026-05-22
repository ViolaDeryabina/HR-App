package com.example.hr_app.presentation.screens.create

// Назначение: Экран для создания вакансиия шаг первый
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026

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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.components.CustomSwitch
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.viewModel.GetDepartmentsViewModel
import com.example.hr_app.presentation.viewModel.GetPositionsViewModel
import com.example.hr_network_library.common.logDebug
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.Comment
import com.example.hr_uikit.presentation.components.BottomSheet
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.model.BottomSheetData
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White
import kotlinx.serialization.Serializable

data class CreateVacancy(
    val title: String,
    val id: Int
)

@Serializable
data class ResultStep1(
    val title: String,
    val salaryRange: String,
    val quantity: Int,
    val depart: String,
    val positions: String,
    val experience: String,
    val city: String,
    val public: Boolean
)

@Composable
fun CreateVacancyStep1(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModelDepartments: GetDepartmentsViewModel,
    viewModelPositions: GetPositionsViewModel,
    sharedPref: SharedPref
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

    val dataDepart by viewModelDepartments.data.collectAsStateWithLifecycle()
    val dataPositions by viewModelPositions.data.collectAsStateWithLifecycle()
    var resultDepart by remember { mutableStateOf<Comment?>(null) }
    var resultPositions by remember { mutableStateOf<Comment?>(null) }

    var showDepart by remember { mutableStateOf(false) }
    var showPositions by remember { mutableStateOf(false) }

    var listDepart by remember { mutableStateOf<List<BottomSheetData>>(emptyList()) }
    var listPositions by remember { mutableStateOf<List<BottomSheetData>>(emptyList()) }

    LaunchedEffect(Unit) {
        val token = sharedPref.getToken()
        if (token != null) {
            viewModelDepartments.getDepartments(token)
            viewModelPositions.getPositions(token)
        }
    }
    LaunchedEffect(dataDepart) {
        if (dataDepart is NetworkResult.Success) {
            resultDepart = (dataDepart as NetworkResult.Success).data
        }
        logDebug("CreateVacancyStep1", "Данные", "$resultDepart")
    }
    LaunchedEffect(dataPositions) {
        if (dataPositions is NetworkResult.Success) {
            resultPositions = (dataPositions as NetworkResult.Success).data
        }
        logDebug("CreateVacancyStep1", "Данные", "$resultPositions")
    }
    LaunchedEffect(title, salaryRange, quantity, depart, positions, experience, city, switch) {
        val currentStep1 = ResultStep1(
            title = title,
            salaryRange = salaryRange,
            quantity = quantity.toIntOrNull() ?: 0,
            depart = depart,
            positions = positions,
            experience = experience,
            city = city,
            public = switch
        )
        sharedPref.saveResultStep1(currentStep1)
    }

    listDepart =
        resultDepart?.items?.mapIndexed { index, item ->
            logDebug("TEST", "item", "${item.text},${item.id}")
            BottomSheetData(
                title = item.id ?: "test",
                selected = false,
                id = index,
            )
        }
            ?: emptyList()

    listPositions =
        resultPositions?.items?.mapIndexed { index, item ->
            logDebug("TEST", "item", "${item.text},${item.id}")
            BottomSheetData(
                title = item.id,
                selected = false,
                id = index,
            )
        } ?: emptyList()



    if (showDepart) {
        BottomSheet(
            onDismissRequest = { showDepart = false },
            onItemSelected = { selectedItem ->
                logDebug("TEST", "item", "${selectedItem.id}")
                // Правильно обновляем состояние списка внутри remember
                listDepart = listDepart.map {
                    it.copy(
                        selected = it.title == selectedItem.title
                    )
                }
            },
            listString = listDepart
        )
    }

    if (showPositions) {
        BottomSheet(
            onDismissRequest = { showPositions = false },
            onItemSelected = { selectedItem ->
                listPositions = listPositions.map { it.copy(selected = it.id == selectedItem.id) }
            },
            listString = listPositions
        )
    }

    Column(
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "General Information",
                color = Primary,
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Step 1 of 3",
                color = Color(0xFF737686),
                style = CustomTheme.typography.BodySmall14px
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Pagination(currentPageIndex = 0)
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
                    placeholder = "e.g. Senior UX Designer",
                    onValueChange = { title = it }
                ),
                CreateVacancyStep(
                    title = "Department",
                    value = depart,
                    main = false,
                    select = true,
                    placeholder = "Product & Design",
                    onValueChange = { depart = it }
                ),
                CreateVacancyStep(
                    title = "Opening Date ",
                    value = title,
                    main = true,
                    select = true,
                    placeholder = "24.05.2024",
                    onValueChange = { depart = it }

                ),
                CreateVacancyStep(
                    title = "Position",
                    value = positions,
                    main = true,
                    select = true,
                    placeholder = "Full-time",
                    onValueChange = { positions = it }

                ),
                CreateVacancyStep(
                    title = "Salary Range",
                    value = salaryRange,
                    main = true,
                    iconFirst = R.drawable.money_app,
                    placeholder = "5,000 - 8,000",
                    onValueChange = { salaryRange = it }

                ),
                CreateVacancyStep(
                    title = "Required Quantity ",
                    value = quantity,
                    main = true,
                    placeholder = "1",
                    onValueChange = { quantity = it }

                ),
                CreateVacancyStep(
                    title = "Experience",
                    value = experience,
                    main = false,
                    placeholder = "e.g. 5+ years",
                    onValueChange = { experience = it }

                ),
                CreateVacancyStep(
                    title = "City",
                    value = city,
                    main = false,
                    placeholder = "New York, NY",
                    iconSecond = R.drawable.point_geo,
                    onValueChange = { city = it }

                ),

                )
            list.forEachIndexed { index, step ->
                Row() {
                    Text(
                        text = step.title,
                        color = Text,
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.SemiBold
                    )
                    if (step.main) {
                        Text(
                            text = "*",
                            color = Error,
                            style = CustomTheme.typography.BodySmall14px,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
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
                            if (index == 1) showDepart =
                                true else if (index == 3) showPositions = true
                        }
                    )
                    Spacer8H()
                }
            }
            Spacer24H()
            Row(modifier) {
                Column(modifier = Modifier.weight(0.8f)) {
                    Text(
                        text = "Public Vacancy",
                        color = Text,
                        style = CustomTheme.typography.Subheader18px,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Show this vacancy on the company career page",
                        color = Color(0xFF191B23),
                        style = CustomTheme.typography.Subheader18px,
                    )
                }
                CustomSwitch(checked = switch, onCheckedChange = { switch = !switch })
            }
            Spacer24H()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE1E2ED), RoundedCornerShape(12.dp))
                    .border(2.dp, Color(0xFFC3C6D7), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Step 2: Candidate Qualifications &\n" +
                            "Skills will appear next",
                    color = Color(0xFF737686),
                    style = CustomTheme.typography.ScreenHeader24px,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateVacancyStep1Prev() {
    CustomTheme {
        //CreateVacancyStep1()
    }
}