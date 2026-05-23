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
import androidx.navigation.NavController
import com.example.hr_app.R
import com.example.hr_app.presentation.components.HeadersWhite
import com.example.hr_app.presentation.helpers.Spacer12W
import com.example.hr_app.presentation.navigation.CandidateCardDetails
import com.example.hr_app.presentation.navigation.PhotoUpdated
import com.example.hr_app.presentation.navigation.VacanciesMain
import com.example.hr_network_library.common.logDebug
import com.example.hr_uikit.presentation.components.BottomBar
import com.example.hr_uikit.presentation.components.Input
import com.example.hr_uikit.presentation.components.PrimaryButton
import com.example.hr_uikit.presentation.model.BottomBarItem
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.Text
import com.example.hr_uikit.presentation.theme.White
import com.example.hr_uikit.R as ui


// Назначение:
// Автор: Дерябина В.Н.
// Дата создания 23-05-2026
@Composable
fun HRBoardMain(
    modifier: Modifier = Modifier,
    navController: NavController
) {
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
        bottomBar = {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.TopEnd
            ) {
                FloatingActionButton(
                    onClick = {
                        logDebug("HRBoardMain", "Переход", "На экран деталей по кандидату")
                        navController.navigate(CandidateCardDetails)
                    },
                    containerColor = Primary,
                    contentColor = White,
                    shape = CircleShape,
                    modifier = Modifier
                        .offset(y = (-80).dp)
                        .padding(end = 24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.stars_three),
                        contentDescription = "",
                        tint = White,
                        modifier = Modifier.size(14.dp)
                    )
                }
                BottomBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFE2E8F0)),
                    selected = 0,
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
                                logDebug("HRBoardMain", "Переход", "На экран Вакансия главная")
                                navController.navigate(VacanciesMain)
                            }
                        ), BottomBarItem(
                            icon = ui.drawable.candidates,
                            text = "Candidates",
                            id = 2,
                            onClick = {
                                logDebug("HRBoardMain", "Переход", "На экран просмотра профиля")
                                navController.navigate(PhotoUpdated)
                            }
                        )
                    )
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
            Input(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = "Search by Full Name...",
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
                },
                trailingIcon = {
                    PrimaryButton(
                        onClick = {}, text = "Done", enabled = true,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun HRBoardMainPrev() {
    CustomTheme {
        //HRBoardMain()
    }
}