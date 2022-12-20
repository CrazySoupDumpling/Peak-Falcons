package com.example.finalproject

import android.os.Bundle
//import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.finalproject.ui.theme.FinalProjectTheme

@Composable
fun EditScreen(
    navController: NavController
) {
    Column(Modifier.fillMaxHeight().background((Color.Red))) {
            Text(text = "Edit Screen", Modifier.align(Alignment.CenterHorizontally))
    }
}
