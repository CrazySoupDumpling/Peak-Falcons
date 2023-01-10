//package com.example.finalproject
//import androidx.navigation.compose.rememberNavController
//import android.util.Log
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.gestures.scrollable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import kotlin.collections.ArrayList
//
//@Composable
//fun ConstraintLayoutContent() {
//    ConstraintLayout {
//        // Create references for the composables to constrain
//        val (button, text) = createRefs()
//
//        Button(
//            onClick = { /* Do something */ },
//            // Assign reference "button" to the Button composable
//            // and constrain it to the top of the ConstraintLayout
//            modifier = Modifier.constrainAs(button) {
//                top.linkTo(parent.top, margin = 16.dp)
//            }
//        ) {
//            Text("Button")
//        }
//
//        // Assign reference "text" to the Text composable
//        // and constrain it to the bottom of the Button composable
//        Text("Text", Modifier.constrainAs(text) {
//            top.linkTo(button.bottom, margin = 16.dp)
//        })
//    }
//}
//
//
//
////@Preview(showBackground = true)
////@Composable
////fun DefaultPreview() {
////    val list = ArrayList<String>()
////    val items = ScheduleItems(list)
////    val schedTest = Schedule("Test1",items)
////
////    list.add("Hi")
////    list.add("Middle")
////    list.add("Bye")
////    list.add("After End")
////    list.add("Later")
////    list.add("Later")
////    list.add("Later")
////    list.add("Later")
////    list.add("Later")
////    list.add("Later")
////    list.add("Later")
////
////    var navController = rememberNavController()
////    //SetupNavGraph(navController = navController, viewModel = null)
////    test(schedTest, navController = navController)
////
////}
//
