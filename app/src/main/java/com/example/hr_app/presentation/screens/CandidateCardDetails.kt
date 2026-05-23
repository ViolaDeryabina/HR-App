package com.example.hr_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hr_app.R
import com.example.hr_app.presentation.components.HeadersWhite
import com.example.hr_app.presentation.helpers.Spacer12H
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer4H
import com.example.hr_app.presentation.helpers.Spacer4W
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.helpers.Spacer8W
import com.example.hr_uikit.presentation.components.Avatar
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

// Назначение: Экран для отображения информации о соискателях
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun CandidateCardDetails(modifier: Modifier = Modifier) {
    var internalNotes by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    Scaffold(
        topBar = {
            HeadersWhite(
                padding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.three_line),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                        Spacer12W()
                        Text(
                            "HR Board",
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Text
                        )
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.filter),
                        contentDescription = "",
                        tint = Primary,
                        modifier = Modifier.size(18.dp, 12.dp)
                    )
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(scroll)
        ) {
            Spacer16H()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.photo_temp),
                        contentDescription = "",
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .border(4.dp, Color(0xFFE7E7F3), CircleShape)
                            .clickable(onClick = {
                            }),
                        contentScale = ContentScale.Crop,

                        )
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color(0xFF22C55E), CircleShape)
                            .border(2.dp, White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                    }
                }
                Spacer16H()
                Text(
                    "Elena Rodriguez",
                    style = CustomTheme.typography.ScreenHeader24px,
                    fontWeight = FontWeight.Bold,
                    color = Text
                )
                Spacer4H()
                Text(
                    "Senior Product Designer",
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.Normal,
                    color = Secondary
                )
            }
            Spacer24H()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F3FE), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                val info = listOf(
                    CandidateCardDetailsInfo(
                        title = "DATE APPLIED",
                        info = "Oct 24, 2023"
                    ),
                    CandidateCardDetailsInfo(
                        title = "CURRENT STATUS",
                        info = "Interviewing"
                    ),
                    CandidateCardDetailsInfo(
                        title = "DEPARTMENT",
                        info = "Design"
                    )
                )
                info.forEachIndexed { index, info ->
                    Text(
                        info.title,
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF737686)
                    )
                    Spacer4H()
                    Text(
                        info.info,
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = if (index == 1) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (index == 1) Primary else Text
                    )
                    Spacer12H()
                }
            }
            Spacer24H()
            Text(
                "Change Pipeline Status",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Text
            )
            Spacer8H()
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Interviewing",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Text
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.calend),
                        contentDescription = "",
                        tint = Primary
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(com.example.hr_uikit.R.drawable.down),
                        contentDescription = "",
                        tint = Color(0xFF737686)
                    )
                }
            )
            Spacer16H()
            Text(
                "HR Specialist",
                style = CustomTheme.typography.FieldLabel14pxBold,
                fontWeight = FontWeight.SemiBold,
                color = Text
            )
            Spacer8H()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF3F3FE), RoundedCornerShape(
                            12.dp
                        )
                    )
                    .border(1.dp, Color(0xFFC3C6D7).copy(0.3f), RoundedCornerShape(21.dp))
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Avatar(bitmap = null, nameShort = "MS", modifier = Modifier.size(32.dp))
                    Spacer8W()
                    Column() {
                        Text(
                            "Michael Scott",
                            style = CustomTheme.typography.BodySmall14px,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF54647A)
                        )
                        Text(
                            "Lead Recruiter",
                            style = CustomTheme.typography.BodySmall14px,
                            fontWeight = FontWeight.Normal,
                            color = Secondary
                        )
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.people_minus),
                    contentDescription = "",
                    tint = Error
                )
            }
            Spacer24H()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Internal Notes",
                    style = CustomTheme.typography.FieldLabel14pxBold,
                    fontWeight = FontWeight.SemiBold,
                    color = Text
                )
                Text(
                    "${internalNotes.length}/500",
                    style = CustomTheme.typography.FieldLabel14pxBold,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color(0xFF737686)
                )
            }
            Spacer8H()
            Input(
                value = internalNotes,
                onValueChange = { internalNotes = it },
                placeholder = {
                    Text(
                        text = "Search by Full Name...",
                        style = CustomTheme.typography.BodyMedium16px,
                        color = Text
                    )
                },
                minLines = 6,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer24H()
            Spacer24H()
            PrimaryButton(
                onClick = {}, text = "Save Changes", enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer16H()
            TextButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.trash),
                    contentDescription = "",
                    tint = Error
                )
                Spacer4W()
                Text(
                    text = "Delete Candidate Card",
                    style = CustomTheme.typography.BodyMedium16px,
                    color = Error
                )
            }
            Spacer24H()
        }
    }
}

data class CandidateCardDetailsInfo(
    val title: String,
    val info: String
)


@Preview
@Composable
private fun CandidateCardDetailsPrev() {
    CustomTheme {
        CandidateCardDetails()
    }
}