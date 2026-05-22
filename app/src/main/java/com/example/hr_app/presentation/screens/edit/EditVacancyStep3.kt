package com.example.hr_app.presentation.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hr_app.R
import com.example.hr_app.data.CreateVacancyStep
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer16W
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.helpers.Spacer8W
import com.example.hr_app.presentation.screens.ChipButtonFill
import com.example.hr_uikit.presentation.components.Avatar
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.Pagination
import com.example.hr_uikit.presentation.components.Select
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения экрана третьего шага редактирования
// Автор: Дерябина В.Н.
// Дата создания 22-05-2026
@Composable
fun EditVacancyStep3(modifier: Modifier = Modifier) {
    var hr by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var person by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
    }
    Pagination(currentPageIndex = 1, progress = true)
    Text(
        text = "Contact Details",
        color = Text,
        style = CustomTheme.typography.ScreenHeader24px,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = "Finalize who will be the main contact for this vacancy.",
        color = Secondary,
        style = CustomTheme.typography.BodySmall14px,
    )
    Spacer(modifier = Modifier.height(32.dp))


    val list = listOf(
        CreateVacancyStep(
            title = "HR Manager Name",
            value = hr,
            main = false,
            placeholder = "Sarah Jenkins",
            select = false,
            onValueChange = { hr = it }
        ),
        CreateVacancyStep(
            title = "Responsible Person",
            value = person,
            main = false,
            select = true,
            placeholder = "Department Lead",
            onValueChange = { person = it }

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

        )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F3FE), RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFFC3C6D7), RoundedCornerShape(8.dp))
                .padding(17.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Avatar(
                    bitmap = ImageBitmap.imageResource(R.drawable.photo_temp),
                )
                Spacer8W()
                Column() {
                    Text(
                        text = "Main HR Point of Contact",
                        color = Text,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Sarah Jenkins",
                        color = Primary,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                    )
                }
            }

        }
        list.forEachIndexed { index, step ->

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F3FE), RoundedCornerShape(8.dp))
                .border(1.dp, Color(0xFFC3C6D7), RoundedCornerShape(8.dp))
                .padding(17.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon),
                    contentDescription = "",
                    tint = Primary
                )
                Spacer16W()
                Column() {
                    Text(
                        text = "Data Privacy Note",
                        color = Text,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "These contact details will be\n" +
                                "visible to shortlisted candidates\n" +
                                "only. Ensure information is\n" +
                                "accurate to maintain recruitment\n" +
                                "SLAs.",
                        color = Secondary,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                    )
                }
            }

        }

    }
    Spacer24H()
}

@Preview
@Composable
private fun EditVacancyStep3Prev() {
    CustomTheme {
        EditVacancyStep3()
    }
}