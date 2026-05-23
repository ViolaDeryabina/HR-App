package com.example.hr_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_app.R
import com.example.hr_app.presentation.components.ActiveFiltersItem
import com.example.hr_app.presentation.components.CandidateStatusItem
import com.example.hr_app.presentation.components.HeadersWhite
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer16W
import com.example.hr_app.presentation.helpers.Spacer4W
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.helpers.Spacer8W
import com.example.hr_uikit.presentation.components.CustomCheckBox
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.components.SecondaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text

// Назначение: Экран для выбора фильтров
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun HRBoardFilters(modifier: Modifier = Modifier) {
    val scroll = rememberScrollState()
    Scaffold(
        topBar = {
            HeadersWhite(
                padding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.close),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                        Spacer12W()
                        Text(
                            "Filters",
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Text
                        )
                    }
                    Text(
                        "Reset",
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
            )
        },
        bottomBar = {
            HeadersWhite(
                modifier = Modifier.fillMaxWidth(),
                padding = PaddingValues(20.dp),
                content = {
                    SecondaryButton(
                        onClick = {},
                        text = "Reset all",
                        enabled = true,
                        modifier = Modifier
                            .weight(0.1f)
                            .height(56.dp)
                    )
                    Spacer16W()
                    PrimaryButton(
                        onClick = {},
                        text = "Apply Filters",
                        enabled = true,
                        modifier = Modifier
                            .weight(0.1f)
                            .height(56.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scroll)
                .padding(horizontal = 20.dp)
        ) {
            Spacer16H()
            Text(
                "Active Filters",
                style = CustomTheme.typography.Subheader18px,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF434655)
            )
            Spacer8H()
            Row() {
                val list = listOf("Product Designer", "Interviewing")
                list.forEach { it ->
                    ActiveFiltersItem(
                        paddingValues = PaddingValues(horizontal = 13.dp, vertical = 7.dp),
                        content = {
                            Text(
                                it,
                                style = CustomTheme.typography.Subheader18px,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF434655)
                            )
                            Spacer8W()
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.close),
                                contentDescription = "",
                                tint = Color(0xFF64748B)
                            )

                        }
                    )
                }
            }
            Spacer8H()
            ActiveFiltersItem(
                paddingValues = PaddingValues(horizontal = 13.dp, vertical = 7.dp),
                content = {
                    Text(
                        "Remote",
                        style = CustomTheme.typography.Subheader18px,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF434655)
                    )
                    Spacer8W()
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.close),
                        contentDescription = "",
                        tint = Color(0xFF64748B)
                    )

                }
            )
            Spacer16H()
            Spacer16H()
            Text(
                "Vacancy",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF434655)
            )
            Spacer16H()
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Search by job title...",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Color(0xFF737686).copy(0.6f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.search),
                        contentDescription = "",
                        tint = Primary
                    )
                }
            )
            Text(
                "Candidate Status",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF434655)
            )
            val list = listOf(
                StatusItem(
                    title = "New Applicants",
                    check = false
                ),
                StatusItem(
                    title = "Interviewing",
                    check = true
                ), StatusItem(
                    title = "Offer Sent",
                    check = false
                ), StatusItem(
                    title = "Hired",
                    check = false
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column() {
                    list.forEachIndexed { index, item ->
                        if (index == 0 || index == 2) {
                            CandidateStatusItem(
                                paddingValues = PaddingValues(
                                    start = 24.dp,
                                    end = 17.dp,
                                ),
                                check = item.check,
                                content = {
                                    Text(
                                        text = item.title,
                                        style = CustomTheme.typography.BodyMedium16px,
                                        color = Color(0xFF737686).copy(0.6f)
                                    )
                                    CustomCheckBox(
                                        checked = item.check,
                                        onCheckedChange = {},
                                        enabled = true
                                    )
                                }
                            )
                            Spacer8H()
                        }
                    }
                }
                Spacer8W()
                Column() {
                    list.forEachIndexed { index, item ->
                        if (index == 1 || index == 3) {
                            CandidateStatusItem(
                                paddingValues = PaddingValues(
                                    start = 24.dp,
                                    end = 17.dp,
                                ),
                                check = item.check,
                                content = {
                                    Text(
                                        text = item.title,
                                        style = CustomTheme.typography.BodyMedium16px,
                                        color = Color(0xFF737686).copy(0.6f)
                                    )
                                    CustomCheckBox(
                                        checked = item.check,
                                        onCheckedChange = {},
                                        enabled = true
                                    )
                                }
                            )
                            Spacer8H()
                        }
                    }
                }
            }


            Spacer16H()
            Spacer16H()
            Text(
                "Department",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF434655)
            )
            Spacer16H()
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Design",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Text
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.home),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )
            Spacer16H()
            Spacer16H()
            Text(
                "City",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF434655)
            )
            Spacer16H()
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "London, New York, Remote...",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Color(0xFF737686).copy(0.6f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.point_geo),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )
            Spacer8H()
            Row() {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEDEDF9), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "POPULAR: SAN FRANCISCO",
                        style = CustomTheme.typography.BodySmall14px,
                        fontSize = 10.sp,
                        color = Color(0xFF54647A).copy(0.6f)
                    )
                }
                Spacer4W()
                Box(
                    modifier = Modifier
                        .background(Color(0xFFEDEDF9), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "BERLIN",
                        style = CustomTheme.typography.BodySmall14px,
                        fontSize = 10.sp,
                        color = Color(0xFF54647A).copy(0.6f)
                    )
                }
            }
            Spacer16H()
            Spacer16H()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Salary Range (Yearly)",
                    style = CustomTheme.typography.FieldLabel14pxBold,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF434655)
                )
            }
            Spacer16H()

        }
    }
}

data class StatusItem(
    val title: String,
    val check: Boolean
)

@Preview
@Composable
private fun HRBoardFiltersPrev() {
    CustomTheme {
        HRBoardFilters()
    }
}