package com.example.hr_app.presentation.screens.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.helpers.Spacer20H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import kotlinx.serialization.Serializable

// Назначение: Экран для создания вакансиия шаг второй
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026

@Serializable
data class ResultStep2(
    val description: String,
    val requirements: String?,
    val technical: String?,
    val testTask: String?,
)

@Composable
fun CreateVacancyStep2(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    sharedPref: SharedPref
) {
    var description by remember { mutableStateOf("") }
    var terms by remember { mutableStateOf("") }
    var requirements by remember { mutableStateOf("") }
    var technical by remember { mutableStateOf("") }
    var testTask by remember { mutableStateOf("") }
    val switch by remember { mutableStateOf(false) }
    val scroll = rememberScrollState()
    LaunchedEffect(description, terms, requirements, technical, testTask) {
        val currentStep1 = ResultStep2(
            description = description,
            requirements = requirements,
            technical = technical,
            testTask = testTask
        )

        sharedPref.saveResultStep2(currentStep1)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Step 2: Details & Requirements",
                color = Primary,
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "60% Complete",
                color = Color(0xFF737686),
                style = CustomTheme.typography.BodySmall14px
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Pagination(currentPageIndex = 0, progress = true)
        val list = listOf(
            CreateVacancyStep(
                title = "Description & Responsibilities",
                value = description,
                main = true,
                placeholder = "Detail the day-to-day responsibilities and overall mission...",
                onValueChange = { description = it }
            ),
            CreateVacancyStep(
                title = "Terms of Employment",
                value = terms,
                main = false,
                placeholder = "Working hours, location, benefits...",
                onValueChange = { terms = it }

            ),
            CreateVacancyStep(
                title = "Requirements",
                value = requirements,
                main = false,
                placeholder = "List specific skills, experience, or certifications needed...",
                onValueChange = { requirements = it }

            ),
            CreateVacancyStep(
                title = "Technical Questions",
                value = technical,
                main = false,
                placeholder = "Specific questions to filter expertise...",
                onValueChange = { technical = it }

            ),
            CreateVacancyStep(
                title = "Test Task",
                value = testTask,
                main = false,
                placeholder = "Brief overview of the practical task if applicable...",
                onValueChange = { testTask = it }

            ),
        )
        list.forEachIndexed { index, step ->
            Row() {
                Text(
                    text = step.title,
                    color = Text,
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.SemiBold,
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
            Spacer8H()
            Input(
                value = step.value,
                onValueChange = { step.onValueChange(it) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                placeholder = {
                    Text(
                        text = step.placeholder,
                        color = Color(0xFF6B7280),
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.Normal
                    )
                }
            )
            Spacer20H()
        }
        Text(
            text = "Support Documents",
            color = Text,
            style = CustomTheme.typography.Subheader18px
        )
    }

}


@Preview
@Composable
private fun CreateVacancyStep2Prev() {
    CustomTheme {
        //CreateVacancyStep2()
    }
}