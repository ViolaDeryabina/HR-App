package com.example.hr_app.presentation.screens.create

// Назначение: Экран для создания вакансиия шаг третий
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.data.ResultStep3
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import kotlinx.serialization.Serializable



@Composable
fun CreateVacancyStep3(
    modifier: Modifier = Modifier,
    sharedPref: SharedPref
) {
    var hr by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var person by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    LaunchedEffect(hr,phone, email, person) {
        val currentStep1 = ResultStep3(
            hr,
            phone,
            email,
            person
        )
        sharedPref.saveResultStep3(currentStep1)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Pagination(currentPageIndex = 3, progress = true)
        Text(
            text = "Contact Details",
            color = Primary,
            style = CustomTheme.typography.FieldLabel14pxBold,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Finalize who will be the main contact for this vacancy.",
            color = Color(0xFF737686),
            style = CustomTheme.typography.BodySmall14px
        )
        val list = listOf(
            CreateVacancyStep(
                title = "HR Manager",
                value = hr,
                main = false,
                placeholder = "Sarah Jenkins",
                select = true,
                onValueChange = { hr = it }
            ),
            CreateVacancyStep(
                title = "Phone Number",
                value = phone,
                main = false,
                select = false,
                iconFirst = R.drawable.phone,
                placeholder = "+1 (415) 888-2930",
                onValueChange = { phone = it }

            ),
            CreateVacancyStep(
                title = "Email Address",
                value = email,
                main = false,
                select = false,
                iconFirst = R.drawable.email,
                placeholder = "s.jenkins@talentengine.corp",
                onValueChange = { email = it }

            ),
            CreateVacancyStep(
                title = "Position ",
                value = person,
                main = false,
                select = true,
                placeholder = "Full-time",
                onValueChange = { person = it }

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
                    value = step.value,
                    onValueChange = { step.onValueChange(it) },
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


@Preview
@Composable
private fun CreateVacancyStep3Prev() {
    CustomTheme {
        //CreateVacancyStep3()
    }
}