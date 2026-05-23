package com.example.hr_app.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
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
import com.example.hr_app.presentation.components.ChipButtonFill
import com.example.hr_app.presentation.components.HeadersWhite
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer16W
import com.example.hr_uikit.presentation.components.BottomBar
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения Applicants
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun ApplicantsMain(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            HeadersWhite(
                padding = PaddingValues(horizontal = 16.dp, vertical = 13.5.dp),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.three_line),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                        Spacer12W()
                        Text(
                            "Candidates",
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Text
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = "",
                            tint = Color(0xFF64748B),
                            modifier = Modifier.size(18.dp, 12.dp)
                        )
                        Spacer12W()
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.settings),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.TopEnd
            ) {
                FloatingActionButton(
                    onClick = {},
                    containerColor = Primary,
                    contentColor = White,
                    shape = CircleShape,
                    modifier = Modifier
                        .offset(y = (-80).dp)
                        .padding(end = 24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.add),
                        contentDescription = "",
                        tint = White,
                        modifier = Modifier.size(14.dp)
                    )
                }
                BottomBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFE2E8F0)),
                    selected = 1
                )
            }


        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer16H()
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Active typing state",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Text
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.search),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )
            val listChip = listOf(
                ListChip(
                    text = "All",
                    check = true,
                    icon = true
                ),
                ListChip(
                    text = "Interviewing",
                    check = false,
                ),
                ListChip(
                    text = "New",
                    check = false,
                ), ListChip(
                    text = "Hired",
                    check = false,
                ), ListChip(
                    text = "Senior",
                    check = false,
                )
            )
            Spacer16H()
            LazyRow() {
                items(listChip) { it ->
                    ChipButtonFill(
                        text = it.text,
                        checked = it.check,
                        onClick = {},
                        iconSecond = it.icon,
                        imageVector = R.drawable.close
                    )
                    Spacer16W()
                }
            }
        }
    }
}

data class ListChip(
    val text: String,
    val check: Boolean,
    val icon: Boolean = false
)

@Preview
@Composable
private fun ApplicantsMainPrev() {
    CustomTheme {
        ApplicantsMain()
    }
}