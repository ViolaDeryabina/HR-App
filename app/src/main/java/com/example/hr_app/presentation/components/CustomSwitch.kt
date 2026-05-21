package com.example.hr_app.presentation.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hr_uikit.presentation.theme.Border
import com.example.hr_uikit.presentation.theme.CustomTheme
import com.example.hr_uikit.presentation.theme.Primary
import com.example.hr_uikit.presentation.theme.White

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        modifier=modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedBorderColor = Primary,
            uncheckedBorderColor = Border,
            checkedThumbColor = White,
            uncheckedThumbColor = White,
            checkedTrackColor = Primary,
            uncheckedTrackColor = Border
        )
    )
}

@Preview
@Composable
private fun CustomSwitchPrev() {
    CustomTheme {
        CustomSwitch(checked=true, onCheckedChange = {})
    }
}