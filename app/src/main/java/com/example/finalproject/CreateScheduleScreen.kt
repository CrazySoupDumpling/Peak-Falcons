package com.example.finalproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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

        val scheduleName = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Schedule Name")
        TextField(
            value = scheduleName.value,
            onValueChange = { scheduleName.value = it },
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor =  colorResource(R.color.SubGreen), cursorColor = colorResource(R.color.SubGreen), placeholderColor = colorResource(R.color.SubGreen), trailingIconColor = colorResource(R.color.SubGreen))
        )
        val items = remember {
            mutableStateListOf<TextFieldValue>()}
        for (counter in 0 until items.size) {
            Text("Item #${counter + 1}")
            Row{
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
        Row{
            Button(onClick = { items.add(TextFieldValue(text = "")) }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                Text(text = "Add Item")
            }
            Button(onClick = {
                if (scheduleName.value.text.isNotEmpty() &&items.size > 0 && items[0].text.isNotEmpty()) {
                    val itemListOfStrings: SnapshotStateList<String> = SnapshotStateList()
                    for (i in 0 until items.size) {
                        itemListOfStrings.add(items[i].text)
                    }
                    viewModel.insertSchedule(
                        Schedule(
                            name = scheduleName.value.text,
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