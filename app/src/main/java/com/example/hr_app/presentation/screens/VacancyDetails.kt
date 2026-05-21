package com.example.hr_app.presentation.screens

// Назначение: 
// Автор: Дерябина В.Н.
// Дата создания 21-05-2026

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hr_app.R
import com.example.hr_app.data.VacancyDetailsMiniCard
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.components.CardMini
import com.example.hr_app.presentation.components.Headers
import com.example.hr_app.presentation.helpers.Spacer12H
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.helpers.Spacer16H
import com.example.hr_app.presentation.helpers.Spacer16W
import com.example.hr_app.presentation.helpers.Spacer24H
import com.example.hr_app.presentation.helpers.Spacer4H
import com.example.hr_app.presentation.helpers.Spacer8H
import com.example.hr_app.presentation.helpers.Spacer8W
import com.example.hr_app.presentation.viewModel.GetInfoById
import com.example.hr_network_library.data.dataSources.NetworkResult
import com.example.hr_network_library.data.dto.VacancyDto
import com.example.hr_uikit.presentation.components.BottomBar
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.components.SecondaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Error
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Secondary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

@Composable
fun VacancyDetails(
    modifier: Modifier = Modifier,
    viewModel: GetInfoById = hiltViewModel(),
    sharedPref: SharedPref = SharedPref(LocalContext.current)
) {
    val scroll = rememberScrollState()
    val data by viewModel.data.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        val id = sharedPref.getVacancyId()
        val token = sharedPref.getToken()

        if (id != null && token != null) {
            viewModel.getInfoById(token, id)
        }
    }
    var result by remember { mutableStateOf<VacancyDto?>(null) }
    LaunchedEffect(data) {
        if (data is NetworkResult.Success) {
            result = (data as NetworkResult.Success).data

        }
    }
    Scaffold(
        topBar = {
            Headers(
                title = "Vacancy Details",
                iconFirst = R.drawable.left,
                tintFirst = Color(0xFF64748B),
                iconSecond = R.drawable.three_point,
                padding = PaddingValues(20.dp)
            )
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE2E8F0)),
                selected = 1
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(scroll)
        ) {
            //IndeterminateCircularIndicator()
            Spacer24H()
            result?.let { it ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box() {
                        Text(
                            text = "Active",
                            color = Color(0xFF15803D),
                            style = CustomTheme.typography.FieldLabel14pxBold,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = it.id.toString(),
                        color = Color(0xFF434655),
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer8H()
                Text(
                    text = it.title,
                    color = Text,
                    style = CustomTheme.typography.ScreenHeader24px,
                    fontWeight = FontWeight.Bold
                )
                Spacer8H()
                Row() {
                    //Icon
                    Text(
                        text = it.testTask,
                        color = Primary,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        fontWeight = FontWeight.SemiBold
                    )
                    //Icon
                    Text(
                        text = "Full-time",
                        color = Primary,
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                val cardMini = listOf(
                    VacancyDetailsMiniCard(
                        title = "Salary",
                        desc = it.salaryRange
                    ),
                    VacancyDetailsMiniCard(
                        title = "Location",
                        desc = it.department
                    )
                )

                val cardMini2 = listOf(
                    VacancyDetailsMiniCard(
                        title = "Experience",
                        desc = it.experience
                    ),
                    VacancyDetailsMiniCard(
                        title = "Openings",
                        desc = it.position
                    )
                )
                Spacer24H()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    cardMini.forEach { item ->
                        CardMini(
                            modifier = Modifier.weight(0.5f),
                            title = item.title,
                            desc = item.desc
                        )
                        Spacer12W()
                    }
                }
                Spacer12H()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    cardMini2.forEach { item ->
                        CardMini(
                            modifier = Modifier.weight(0.5f),
                            title = item.title,
                            desc = item.desc
                        )
                        Spacer12W()
                    }
                }
                Spacer24H()
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.cart),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Spacer24H()
                Text(
                    text = "Role Overview",
                    color = Text,
                    style = CustomTheme.typography.Subheader18px,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer16H()
                Text(
                    text = it.description,
                    modifier= Modifier.fillMaxWidth(),
                    color = Color(0xFF434655),
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.Normal
                )
                Spacer16H()
                Text(
                    text = it.termsOfEmployments,
                    modifier= Modifier.fillMaxWidth(),
                    color = Color(0xFF434655),
                    style = CustomTheme.typography.BodyMedium16px,
                    fontWeight = FontWeight.Normal
                )
                Spacer16H()
                Text(
                    text = "HR Manager",
                    color = Text,
                    style = CustomTheme.typography.Subheader18px,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer16H()
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF3F3FE), RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .size(44.dp)
                            .background(White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.photo_temp),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize().clip(CircleShape)
                        )
                    }
                    Spacer16W()
                    Column(modifier = Modifier.weight(0.4f)) {
                        Text(
                            text = "Sarah Jenkins",
                            color = Text,
                            style = CustomTheme.typography.ScreenHeader24px,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Senior Talent Acquisition",
                            color = Secondary,
                            style = CustomTheme.typography.BodySmall14px,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .size(40.dp)
                            .background(White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.email),
                            contentDescription = "",
                            tint = Primary
                        )
                    }
                    Spacer8W()
                    Box(
                        modifier = Modifier
                            .weight(0.1f)
                            .size(40.dp)
                            .background(White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.sms),
                            contentDescription = "",
                            tint = Primary
                        )
                    }
                }
                Text(
                    text = "Attachments",
                    color = Text,
                    style = CustomTheme.typography.Subheader18px,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer16H()
                val listAttachmentsData = listOf(
                    AttachmentsData(
                        icon = R.drawable.pdf,
                        title = "Job_Description.pdf",
                        iconSecond = R.drawable.down_
                    ),
                    AttachmentsData(
                        icon = R.drawable.file,
                        title = "Benefits_Package.pdf",
                        iconSecond = R.drawable.down_
                    )
                )
                listAttachmentsData.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White, RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row() {
                            Icon(
                                imageVector = ImageVector.vectorResource(item.icon),
                                contentDescription = "",
                                tint = if (item.title == "Job_Description.pdf") Error else Primary
                            )
                            Spacer8W()
                            Text(
                                text = item.title,
                                color = Text,
                                style = CustomTheme.typography.Subheader18px,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(item.iconSecond),
                            contentDescription = "",
                            tint = Secondary
                        )
                    }
                    Spacer4H()
                }
                Spacer24H()
                Spacer24H()

                PrimaryButton(
                    onClick = {},
                    text = "Edit Vacancy",
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
                SecondaryButton(
                    onClick = {},
                    text = "Delete Vacancy",
                    enabled = true, modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

data class AttachmentsData(
    val icon: Int,
    val title: String,
    val iconSecond: Int
)

@Preview
@Composable
private fun VacancyDetailsPrev() {
    CustomTheme {
        VacancyDetails()
    }
}