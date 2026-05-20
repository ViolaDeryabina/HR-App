package com.example.hr_app.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.hr_app.domain.SharedPref
import com.example.hr_app.presentation.viewModel.VacancyViewModel
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.components.BottomBar
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.components.SecondaryButton
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White

@Composable
fun VacanciesMain(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    viewModel: VacancyViewModel,
    sharedPref: SharedPref
) {
    val scroll = rememberScrollState()
    val data by viewModel.data.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        val token = sharedPref.getToken()
        logDebug("VacanciesMain", "Получение токена", "$token")
        if(token!=null){
            viewModel.getVacancy(token)
        }

    }
    LaunchedEffect(data) {
        logDebug("VacanciesMain", "Получение данных", "$data")
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .border(1.dp, Color(0xFFE2E8F0))
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Vacancies",
                    style = CustomTheme.typography.FieldLabel14pxBold,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = Text
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.settings),
                    contentDescription = "",
                    tint = Color(0xFF64748B)
                )
            }
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFE2E8F0)),
                selected = 1
            )
        },
        containerColor = Color(0xffFAF8FF)
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
                SecondaryButton(
                    onClick = {},
                    text = "Configure Tables",
                    enabled = true,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                PrimaryButton(
                    onClick = {}, text = "+ Create Vacancy", enabled = true,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .background(White, RoundedCornerShape(12.dp))
                    .border(
                        1.dp,
                        color = Color(0xFFC3C6D7).copy(0.3f),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Input(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            "Search by title...",
                            style = CustomTheme.typography.BodyMedium16px,
                            color = Color(0xFF6B7280)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.search),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))

                PrimaryButton(
                    onClick = {},
                    text = "Search",
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "12 vacancies found",
                        style = CustomTheme.typography.FieldLabel14pxBold,
                        color = Color(0xFF6B7280)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier

                            .border(1.dp, Color(0xFFC3C6D7), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = "",
                            tint = Color(0xFF64748B)
                        )
                        Text(
                            "Apply Filters.",
                            style = CustomTheme.typography.BodyMedium16px,
                            color = Color(0xFF6B7280)
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}

@Preview
@Composable
private fun VacanciesMainPrev() {
    CustomTheme {
        //VacanciesMain()
    }
}