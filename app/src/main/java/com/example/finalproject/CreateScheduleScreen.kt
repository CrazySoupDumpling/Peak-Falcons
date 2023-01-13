package com.example.finalproject

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateScheduleScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Column(modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState()).background(colorResource(R.color.Background))){
        val numOfItems: Int = 1;
        Row( modifier = Modifier.fillMaxWidth()){
            Text(text = "Create A New Schedule", modifier = Modifier
                .padding(all = 20.dp)
                .padding(top = 15.dp),
                fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Spacer(modifier = Modifier.width(70.dp))
            Button(onClick = {navController.navigate(route = Screens.Edit.route)},
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))) {
                Text(text = "Done ", modifier = Modifier.padding(all = 20.dp))
            }
        }

        val ScheduleName = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Schedule Name")
        TextField(
            value = ScheduleName.value,
            onValueChange = { ScheduleName.value = it },
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor =  colorResource(R.color.SubGreen), cursorColor = colorResource(R.color.SubGreen), placeholderColor = colorResource(R.color.SubGreen), trailingIconColor = colorResource(R.color.SubGreen))
        )
        val items = remember {
            mutableStateListOf<TextFieldValue>()}
        for (counter in 0..items.size-1) {
            Text("Item #${counter + 1}")
            Row(){
                TextField(
                    value = items[counter],
                    onValueChange = { items[counter] = it },
                    colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor =  colorResource(R.color.SubGreen), cursorColor = colorResource(R.color.SubGreen), placeholderColor = colorResource(R.color.SubGreen), trailingIconColor = colorResource(R.color.SubGreen))
                )
                Button(onClick ={items.removeAt(counter)},colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)){
                    Text(text = "Delete")
                }
            }
        }
        Row() {
            Button(onClick = { items.add(TextFieldValue(text = "")) }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                Text(text = "Add Item")
            }
            Button(onClick = {
                if (ScheduleName.value.text.length > 0 &&items.size > 0 && items[0].text.length > 0) {
                    var itemListOfStrings: SnapshotStateList<String> = SnapshotStateList<String>()
                    for (i in 0..items.size - 1) {
                        itemListOfStrings.add(items[i].text)
                    }
                    viewModel.insertSchedule(
                        Schedule(
                            name = ScheduleName.value.text,
                            items = ScheduleItems(itemListOfStrings)
                        )
                    )
                    navController.navigate(Screens.Schedule.route)
                }
            }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))){
                Text(text = "Save Schedule")
            }
        }
    }

}