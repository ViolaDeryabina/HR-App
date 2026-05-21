package com.example.hr_app.presentation.screens

// Назначение: Экран для создания вакансиия шаг третий
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.presentation.components.Headers
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

@Composable
fun CreateVacancyStep3(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var hr by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var person by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
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
                                "CreateVacancyStep3",
                                "Переход",
                                "Нв экрана просмотра вакансий"
                            )
                            navController.navigate(VacanciesMain)
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
                    text = "Contact Details",
                    color = Primary,
                    style = CustomTheme.typography.FieldLabel14pxBold,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Step 3 of 3",
                    color = Color(0xFF737686),
                    style = CustomTheme.typography.BodySmall14px
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Pagination(currentPageIndex = 3, progress = true)
            val list = listOf(
                CreateVacancyStep(
                    title = "HR Manager",
                    value = hr,
                    main = false,
                    placeholder = "Sarah Jenkins",
                    select = true
                ),
                CreateVacancyStep(
                    title = "Phone Number",
                    value = phone,
                    main = false,
                    select = false,
                    iconFirst = R.drawable.phone,
                    placeholder = "+1 (415) 888-2930"
                ),
                CreateVacancyStep(
                    title = "Email Address",
                    value = email,
                    main = false,
                    select = false,
                    iconFirst = R.drawable.email,
                    placeholder = "s.jenkins@talentengine.corp"
                ),
                CreateVacancyStep(
                    title = "Position ",
                    value = person,
                    main = false,
                    select = true,
                    placeholder = "Full-time",
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
            Spacer8H()
            Text(
                text = "This person will be notified about final candidates.",
                color = Color(0xFF737686),
                style = CustomTheme.typography.BodySmall14px
            )
            Spacer24H()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFDBE1FF), RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, Color(0xFF004AC6).copy(0.1f), RoundedCornerShape(12.dp))
                    .padding(24.dp),

                contentAlignment = Alignment.BottomEnd
            ) {
                Column() {
                    Text(
                        text = "Final Review",
                        color = Text,
                        style = CustomTheme.typography.Subheader18px
                    )
                    Text(
                        text = "This person will be notified about final candidates.",
                        color = Primary,
                        style = CustomTheme.typography.BodySmall14px
                    )
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.task),
                    contentDescription = "",
                    tint = Color(0xFF737686),
                    modifier = Modifier.offset(x = (25).dp, y = (25).dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateVacancyStep3Prev() {
    CustomTheme {
        //CreateVacancyStep3()
    }
}