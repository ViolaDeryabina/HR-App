package com.example.hr_app.presentation.screens

// Назначение: Экран для создания вакансиия шаг первый
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.hr_app.presentation.components.Headers
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.navigation.CreateVacancyStep2
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

data class CreateVacancy(
    val title: String,
    val id: Int
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
    var quantity by remember { mutableIntStateOf(0) }
    var experience by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    val switch by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()

    val dataDepart by viewModelDepartments.data.collectAsStateWithLifecycle()
    val dataPositions by viewModelPositions.data.collectAsStateWithLifecycle()
    var resultDepart by remember { mutableStateOf<Comment?>(null) }
    var resultPositions by remember { mutableStateOf<Comment?>(null) }
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

    val listDepart: List<BottomSheetData> =
        resultDepart?.items?.mapIndexed { index, item ->
            BottomSheetData(
                title = item.text,
                selected = false,
                id = index,
            )
        }
    )

    BottomSheet(
        onDismissRequest = {}, onItemSelected = {},
        listString = listDepart,

        )
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
                            navController.navigate(CreateVacancyStep2)
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
                        placeholder = "e.g. Senior UX Designer"
                    ),
                    CreateVacancyStep(
                        title = "Department",
                        value = title,
                        main = false,
                        select = true,
                        placeholder = "Product & Design"
                    ),
                    CreateVacancyStep(
                        title = "Opening Date ",
                        value = title,
                        main = true,
                        select = true,
                        placeholder = "24.05.2024"
                    ),
                    CreateVacancyStep(
                        title = "Position ",
                        value = title,
                        main = true,
                        select = true,
                        placeholder = "Full-time"
                    ),
                    CreateVacancyStep(
                        title = "Salary Range",
                        value = salaryRange,
                        main = true,
                        iconFirst = R.drawable.money_app,
                        placeholder = "5,000 - 8,000"
                    ),
                    CreateVacancyStep(
                        title = "Required Quantity ",
                        value = salaryRange,
                        main = true,
                        placeholder = "1"
                    ),
                    CreateVacancyStep(
                        title = "Experience",
                        value = experience,
                        main = false,
                        placeholder = "e.g. 5+ years"
                    ),
                    CreateVacancyStep(
                        title = "City",
                        value = city,
                        main = false,
                        placeholder = "New York, NY",
                        iconSecond = R.drawable.point_geo
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
                            onValueChange = { step.value = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = step.placeholder,
                                    color = Color(0xFF6B7280),
                                    style = CustomTheme.typography.BodyMedium16px,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            leadingIcon = {
                                if (step.iconFirst != null) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(step.iconFirst),
                                        contentDescription = "",
                                        tint = Color(0xFF737686)
                                    )
                                }
                            },
                            trailingIcon = {
                                if (step.iconSecond != null) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(step.iconSecond),
                                        contentDescription = "",
                                        tint = Color(0xFF737686)
                                    )
                                }
                            }
                        )
                        Spacer8H()
                    } else {
                        Select(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = step.placeholder,
                                    color = Color(0xFF191B23),
                                    style = CustomTheme.typography.BodyMedium16px,
                                    fontWeight = FontWeight.Normal
                                )
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
                    CustomSwitch(checked = switch, onCheckedChange = {})
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
}

@Preview
@Composable
private fun CreateVacancyStep1Prev() {
    CustomTheme {
        //CreateVacancyStep1()
    }
}