package com.example.finalproject

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

@Composable
fun EditScheduleScreen(
    scheduleID: Int,
    navController: NavController,
    viewModel: MainViewModel
) {
    viewModel.findSchedule(scheduleID)
    val context = LocalContext.current
    val schedule: Schedule? =  viewModel.searchResults.observeAsState().value?.get(0)
    val itemList: SnapshotStateList<String> = if (schedule != null) {
        remember {schedule.items.scheduleItems }
    }else{
        remember {SnapshotStateList() }
    }
    val timerList: SnapshotStateList<String> = if (schedule != null) {
        remember {schedule.items.scheduleTimers }
    }else{
        remember {SnapshotStateList() }
    }

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    var alarmTime = remember{mutableStateOf("")}
    val newScheduleName = remember { mutableStateOf(TextFieldValue()) }
    Column(modifier = Modifier.fillMaxHeight().background(colorResource(R.color.Background))) {
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(1f)) {
            if (schedule != null) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = schedule.name, modifier = Modifier
                            .padding(all = 10.dp)
                            .weight(1f),
                        fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { navController.navigate(route = Screens.Edit.route) },
                        modifier = Modifier.width(150.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))
                    ) {
                        Text(text = "Exit", modifier = Modifier.padding(all = 20.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth()) {

                    val mTimePickerDialog = TimePickerDialog(
                        context,
                        {_, mHour : Int, mMinute: Int ->
                            alarmTime.value = "$mHour:$mMinute"
                        }, mHour, mMinute, false
                    )
                    TextField(
                        value = newScheduleName.value,
                        label = { Text("Change schedule name") },
                        onValueChange = { newScheduleName.value = it },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(R.color.SubGreen),
                            cursorColor = colorResource(R.color.SubGreen),
                            placeholderColor = colorResource(R.color.SubGreen),
                            trailingIconColor = colorResource(R.color.SubGreen)
                        )
                    )
                    Button(
                        onClick = { mTimePickerDialog.show() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
                    ) {
                        Text(text = "Change Start Time", color = Color.White, fontSize = 10.sp)
                    }
                }
                for (i in 0 until itemList.size) {

                    val timeAllocated = remember { mutableStateOf(TextFieldValue(schedule.items.scheduleTimers[i])) }

                    val temp = timeAllocated.value
                    var openDialog by remember{ mutableStateOf(false) }
                    if(openDialog){AlertDialog(

                        onDismissRequest = {},
                        title = {
                            Text(text = "Set new time limit")
                        },
                        text = {
                            TextField(
                                value = timeAllocated.value,
                                label = { Text("Enter number of minutes allocated") },
                                onValueChange = { timeAllocated.value = it },
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = colorResource(R.color.SubGreen),
                                    cursorColor = colorResource(R.color.SubGreen),
                                    placeholderColor = colorResource(R.color.SubGreen),
                                    trailingIconColor = colorResource(R.color.SubGreen)
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        },
                        confirmButton = {
                            Row(
                                modifier = Modifier

                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = {
                                        timerList[i] = temp.text
                                        timeAllocated.value = temp
                                        openDialog = false
                                    },
                                    modifier = Modifier.width(150.dp), colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                                ) {
                                    Text(text = "Cancel", modifier = Modifier.padding(all = 10.dp), fontSize = 11.sp)
                                }

                                Button(
                                    onClick = {
                                        openDialog = false
                                        timerList[i] = timeAllocated.value.text
                                    }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen)),
                                    modifier = Modifier.width(150.dp)
                                ) {

                                    Text(text = "Confirm", modifier = Modifier.padding(all = 10.dp), fontSize = 11.sp)
                                }
                            }
                        }


                    )
                    }

                    Text("Task #${i + 1}", modifier = Modifier.padding(all = 10.dp))
                    Row {
                        TextField(
                            value = itemList[i],
                            onValueChange = { itemList[i] = it },
                            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor =  colorResource(R.color.SubGreen), cursorColor = colorResource(R.color.SubGreen), placeholderColor = colorResource(R.color.SubGreen), trailingIconColor = colorResource(R.color.SubGreen))
                        )
                        Button(onClick = { itemList.removeAt(i);timerList.removeAt(i) }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                            Text("Delete")
                        }
                    }
                    Button(onClick = { openDialog = true }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
                        Text(text = "Change Time Limit", color = Color.White)
                    }
                }
            }
            Button(onClick = {itemList.add(""); timerList.add("")}, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))){
                Text("Add Task")
            }
        }
        Column{
            Row {
                Button(onClick = {
                    if (schedule != null) {
                        if(schedule.name != newScheduleName.value.text && newScheduleName.value.text != "" ) {
                            schedule.name = newScheduleName.value.text
                        }
                        schedule.items = ScheduleItems(itemList, timerList)
                        Log.e("e",alarmTime.value)
//                        alarmTime = "12:12"
                        if(alarmTime.value != ""){
                            schedule.startTime = alarmTime.value
                        }

                        viewModel.updateSchedule(schedule)
                        navController.navigate(Screens.Edit.route)
                    }


                }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))) {
                    Text("Save")
                }

            }
        }
    }

}
