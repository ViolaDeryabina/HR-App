package com.example.hr_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.hr_app.presentation.screens.Authorization
import com.example.hr_uikit.presentation.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Authorization(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}