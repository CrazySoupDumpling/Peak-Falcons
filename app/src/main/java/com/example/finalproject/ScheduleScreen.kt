package com.example.finalproject

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.theme.FinalProjectTheme
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer

@Composable
fun ScheduleScreen(
    schedules: List<Schedule>,
    navController: NavController,
    viewModel: MainViewModel
) {


    /* DATABASE TESTING

    var scheduleName by remember { mutableStateOf("") }
    var scheduleQuantity by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    val onProductTextChange = { text : String ->
        scheduleName = text
    }

    val onQuantityTextChange = { text : String ->
        scheduleQuantity = text
    }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            viewModel.searchResults.observeAsState().value?.get(0)?.items?.scheduleItems?.get(0)
                    .toString()

            )
        CustomTextField(
            title = "Product Name",
            textState = scheduleName,
            onTextChange = onProductTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Quantity",
            textState = scheduleQuantity,
            onTextChange = onQuantityTextChange,
            keyboardType = KeyboardType.Number
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (scheduleQuantity.isNotEmpty()) {
                    viewModel.insertSchedule(
                        Schedule(
                            scheduleName,
                            ScheduleItems(listOf(scheduleQuantity, "hi"))
                        )
                    )
                    searching = false
                }
            }) {
                Text("Add")
            }

            Button(onClick = {
                searching = true
                viewModel.findSchedule(scheduleName)
            }) {
                Text("Search")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteSchedule(scheduleName)
            }) {
                Text("Delete")
            }

            Button(onClick = {
                searching = false
                scheduleName = ""
                scheduleQuantity = ""
            }) {
                Text("Clear")
            }
        }
    }

     */

    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())) {

        Row(Modifier.fillMaxWidth()) {
            Text(text = "Your Schedules", modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 15.dp))
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {navController.navigate(route = Screens.Edit.route)}, modifier = Modifier.width(150.dp)) {
                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
            }
        }
        for(i in 0..schedules.size-1){
            Button(onClick = {navController.navigate(route= "${Screens.Checklist.route}/${schedules[i].id}") },modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
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

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
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