package com.example.finalproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScheduleScreen(
    schedules: List<Schedule>,
    navController: NavController,
) {
    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.Background))) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 15.dp)
                , fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Edit.route)}, modifier = Modifier.width(150.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
            }
        }
        for(i in schedules.indices){
            Button(onClick = {navController.navigate(route= "${Screens.Checklist.route}/${schedules[i].id}") },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp) , colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                Text(
                    text = schedules[i].name,
                    Modifier
                        .padding(all = 20.dp)
                        .padding(top = 20.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FinalProjectTheme {
//        val list = ArrayList<Schedule>()
//        list.add(Schedule("Hi"))
//        list.add(Schedule("Middle"))
//        list.add(Schedule("Bye"))
//        list.add(Schedule("After End"))
//        list.add(Schedule("Later"))
//        var navController = rememberNavController()
//        SetupNavGraph(navController = navController)
//        ScheduleScreen(list, navController = navController)
//    }
//}