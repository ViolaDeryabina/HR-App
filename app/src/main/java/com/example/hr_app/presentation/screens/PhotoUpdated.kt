package com.example.hr_app.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hr_app.R
import com.example.hr_app.presentation.components.HeadersWhite
import com.example.hr_app.presentation.helpers.Spacer12H
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer16W
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.components.BottomBar
import com.example.hr_uikit.presentation.components.BottomSheetTemp
import com.example.hr_uikit.presentation.components.SecondaryButton
import com.example.hr_uikit.presentation.model.BottomBarItem
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Tertiary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White
import com.example.hr_uikit.R as ui

// Назначение: Экран для просмотра профиля и его изменения
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun PhotoUpdated(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    val scroll = rememberScrollState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                logDebug("PhotoUpdated", "Selected URI", "$uri")
            } else {
                logDebug("PhotoUpdated", "Selected URI", "No media selected")
            }
        }
    Scaffold(
        topBar = {
            HeadersWhite(
                padding = PaddingValues(horizontal = 20.dp, vertical = 15.5.dp),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.left),
                            contentDescription = "",
                            tint = Primary
                        )
                        Spacer12W()
                        Text(
                            "Update Profile Photo",
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Text
                        )
                    }
                    Text(
                        "MTE",
                        style = CustomTheme.typography.BodyMedium16px,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
            )
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE2E8F0)),
                selected = 2,
                list = listOf(
                    BottomBarItem(
                        icon = ui.drawable.dashboard,
                        text = "Dashboard",
                        id = 0,
                        onClick = {}
                    ), BottomBarItem(
                        icon = ui.drawable.vacancies,
                        text = "Vacancies",
                        id = 1,
                        onClick = {
                            logDebug("PhotoUpdated", "Переход", "На экран Вакансия главная")
                            navController.navigate(VacanciesMain)
                        }
                    ), BottomBarItem(
                        icon = ui.drawable.candidates,
                        text = "Candidates",
                        id = 2
                    )
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp)
                .verticalScroll(scroll)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, RoundedCornerShape(12.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Spacer16H()
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.photo_temp),
                        contentDescription = "",
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                            .border(4.dp, Primary, CircleShape)
                            .clickable(onClick = {
                                showBottomSheet = true
                            }),
                        contentScale = ContentScale.Crop,

                        )
                    Box(
                        modifier = Modifier
                            .size(33.dp, 32.dp)
                            .background(Primary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.verif),
                            contentDescription = "",
                            tint = White
                        )
                    }
                }
                Spacer16H()
                SecondaryButton(
                    onClick = {}, text = "Change Photo", enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer16H()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White, RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFFF1F5F9), RoundedCornerShape(12.dp))
                    .padding(24.dp),

                ) {
                Text(
                    "Julian Sterling",
                    style = CustomTheme.typography.ScreenHeader24px,
                    fontWeight = FontWeight.Bold,
                    color = Text
                )
                Spacer8H()
                Text(
                    "Senior Frontend Developer",
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.Normal,
                    color = Secondary
                )
                Spacer12H()
                Row() {
                    val list = listOf(
                        ListDataRow(
                            text = "Shortlisted",
                            Color(0xFFDBE1FF),
                            Color(0xFF003EA8),
                        ),
                        ListDataRow(
                            text = "New York, NY",
                            Color(0xFFD0E1FB),
                            Color(0xFF54647A),
                        ),
                        ListDataRow(
                            text = "Applied 2d ago",
                            Color(0xFFFFDBCD),
                            Color(0xFF7D2D00),
                        )
                    )
                    list.forEach { it ->
                        Box(
                            modifier = Modifier
                                .background(it.color, CircleShape)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                it.text,
                                style = CustomTheme.typography.BodyMedium16px,
                                fontSize = 12.sp,
                                color = it.colorText
                            )
                        }
                    }

                }
                Spacer24H()
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(0.1f)) {
                        Text(
                            "Experience",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Secondary
                        )
                        Text(
                            "8 Years",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Text
                        )
                    }

                    Column(modifier = Modifier.weight(0.1f)) {
                        Text(
                            "Notice Period",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Secondary
                        )
                        Text(
                            "Immediate",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Text
                        )
                    }
                }
            }
            Spacer16H()
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
                Row() {
                    Box(
                        modifier = Modifier
                            .size(40.dp, 44.dp)
                            .background(White, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.file),
                            contentDescription = "",
                            tint = Tertiary
                        )
                    }
                    Column() {
                        Text(
                            "resume_sterling_final.pdf",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Text
                        )
                        Text(
                            "Last updated Oct 12, 2023",
                            style = CustomTheme.typography.BodyMedium16px,
                            fontWeight = FontWeight.Bold,
                            color = Text
                        )
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.down_),
                    contentDescription = "",
                    tint = Secondary
                )
            }
            Spacer16H()
            Spacer16H()
            val listCol = listOf(
                ListDataColumn(
                    R.drawable.sms,
                    "Interviews",
                    Primary,
                    data = 3f
                ),
                ListDataColumn(
                    R.drawable.star,
                    "Score",
                    Tertiary,
                    data = 9.4f
                ),
                ListDataColumn(
                    R.drawable.vacancy,
                    "Offers",
                    Primary,
                    data = 1f
                ),
                ListDataColumn(
                    R.drawable.clock,
                    "Stages",
                    Secondary,
                    data = 5f
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                listCol.forEachIndexed { index, column ->
                    if (index < 2) {
                        Column(
                            modifier = Modifier
                                .weight(0.1f)
                                .background(
                                    Color(0xFFF3F3FE), RoundedCornerShape(
                                        12.dp
                                    )
                                )
                                .border(
                                    1.dp,
                                    Color(0xFFC3C6D7).copy(0.3f),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 18.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(column.icon),
                                contentDescription = "",
                                tint = column.colorIcon
                            )
                            Text(
                                column.text,
                                style = CustomTheme.typography.FieldLabel14pxBold,
                                fontWeight = FontWeight.SemiBold,
                                color = Text
                            )
                            Text(
                                "${column.data}",
                                style = CustomTheme.typography.ScreenHeader24px,
                                fontWeight = FontWeight.Bold,
                                color = Text
                            )
                        }
                        Spacer16W()
                    }
                }
            }
            Spacer16H()
            Row(modifier = Modifier.fillMaxWidth()) {
                listCol.forEachIndexed { index, column ->
                    if (index >= 2) {
                        Column(
                            modifier = Modifier
                                .weight(0.1f)
                                .background(
                                    Color(0xFFF3F3FE), RoundedCornerShape(
                                        12.dp
                                    )
                                )
                                .border(
                                    1.dp,
                                    Color(0xFFC3C6D7).copy(0.3f),
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 18.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(column.icon),
                                contentDescription = "",
                                tint = column.colorIcon
                            )
                            Text(
                                column.text,
                                style = CustomTheme.typography.FieldLabel14pxBold,
                                fontWeight = FontWeight.SemiBold,
                                color = Text
                            )
                            Text(
                                "${column.data}",
                                style = CustomTheme.typography.ScreenHeader24px,
                                fontWeight = FontWeight.Bold,
                                color = Text
                            )
                        }
                        Spacer16W()
                    }
                }
            }
        }
        if (showBottomSheet) {
            BottomSheetTemp(
                onDismissRequest = {},
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Update Profile Photo",
                            style = CustomTheme.typography.Subheader18px,
                            fontWeight = FontWeight.SemiBold,
                            color = Text
                        )
                        Spacer16H()
                        Text(
                            "Choose a new profile photo for the candidate profile.",
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontWeight = FontWeight.Normal,
                            color = Secondary,
                            textAlign = TextAlign.Center
                        )
                        Spacer16H()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color(0xFFDBE1FF), CircleShape),
                                contentAlignment = Alignment.Center

                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.file),
                                    contentDescription = "",
                                    tint = Color(0xFF00174B)
                                )
                            }
                            Spacer16W()
                            Text(
                                "Take Photo",
                                style = CustomTheme.typography.BodyMedium16px,
                                fontWeight = FontWeight.SemiBold,
                                color = Text
                            )
                        }
                        Spacer16H()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {

                                }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color(0xFFDBE1FF), CircleShape),
                                contentAlignment = Alignment.Center

                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.file),
                                    contentDescription = "",
                                    tint = Color(0xFF00174B)
                                )
                            }
                            Spacer16W()
                            Text(
                                "Choose from Gallery",
                                style = CustomTheme.typography.BodyMedium16px,
                                fontWeight = FontWeight.SemiBold,
                                color = Text
                            )
                        }
                        TextButton(onClick = {
                            showBottomSheet = false
                        }) {
                            Text(
                                "Cancel",
                                style = CustomTheme.typography.BodyMedium16px,
                                fontWeight = FontWeight.SemiBold,
                                color = Error
                            )
                        }
                    }
                }

            )
        }

    }
}


data class ListDataRow(
    val text: String,
    val color: Color,
    val colorText: Color

)

data class ListDataColumn(
    val icon: Int,
    val text: String,
    val colorIcon: Color,
    val data: Float

)

@Preview
@Composable
private fun PhotoUpdatedPrev() {
    CustomTheme {
        PhotoUpdated()
    }
}