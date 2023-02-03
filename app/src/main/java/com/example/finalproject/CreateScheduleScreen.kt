package com.example.finalproject

import android.app.TimePickerDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*


@Composable
fun CreateScheduleScreen(
    navController: NavController,
    viewModel: MainViewModel
) {


    Column(modifier = Modifier.fillMaxHeight().background(colorResource(R.color.Background))) {

        val scheduleName = remember { mutableStateOf(TextFieldValue()) }
        val items = remember {
            mutableStateListOf<TextFieldValue>()
        }

        val timerTimes = remember {
            mutableStateListOf<String>()
        }
        var alarmTime = ""

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = "Create a New Schedule", modifier = Modifier
                        .padding(all = 10.dp)
                        .weight(1f, false),
                    fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(0.5f))
                Button(
                    onClick = { navController.navigate(route = Screens.Edit.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))
                ) {
                    Text(text = "Done ", modifier = Modifier.padding(all = 20.dp))
                }
            }
            val context = LocalContext.current
            val mCalendar = Calendar.getInstance()
            val mHour = mCalendar[Calendar.HOUR_OF_DAY]
            val mMinute = mCalendar[Calendar.MINUTE]




            Text(text = "Schedule Name")
            Row(modifier = Modifier.fillMaxWidth()) {

                val mTimePickerDialog = TimePickerDialog(
                    context,
                    {_, mHour : Int, mMinute: Int ->
                        alarmTime = "$mHour:$mMinute"
                    }, mHour, mMinute, false
                )
                TextField(
                    value = scheduleName.value,
                    onValueChange = { scheduleName.value = it },
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
                    Text(text = "Set Start Time", color = Color.White, fontSize = 13.sp)
                }
            }





            for (counter in 0 until items.size) {

                val mTimePickerDialog = TimePickerDialog(
                    context,
                    { _, mHour: Int, mMinute: Int ->
                        timerTimes[counter] = "$mHour:$mMinute"
                    }, mHour, mMinute, false
                )

                Text("Task #${counter + 1}")
                Row {
                    TextField(
                        value = items[counter],
                        onValueChange = { items[counter] = it },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(
                                R.color.SubGreen
                            ),
                            cursorColor = colorResource(R.color.SubGreen),
                            placeholderColor = colorResource(R.color.SubGreen),
                            trailingIconColor = colorResource(R.color.SubGreen)
                        )
                    )
                    Button(
                        onClick = { items.removeAt(counter) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(text = "Delete")
                    }
                }
                Button(
                    onClick = { mTimePickerDialog.show() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
                ) {
                    Text(text = "Set Time Limit", color = Color.White)
                }
            }
            Row {
                Button(
                    onClick = { items.add(TextFieldValue(text = ""));timerTimes.add("") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                ) {
                    Text(text = "Add Task")
                }
            }
        }
            Column {
                Row {
                    Button(
                        onClick = {
                            if (scheduleName.value.text.isNotEmpty() && items.size > 0 && items[0].text.isNotEmpty()) {
                                val itemListOfStrings: SnapshotStateList<String> =
                                    SnapshotStateList()
                                val timerListOfStrings: SnapshotStateList<String> =
                                    SnapshotStateList()
                                for (i in 0 until items.size) {
                                    itemListOfStrings.add(items[i].text)
                                    timerListOfStrings.add(timerTimes[i])
                                }
                                val alarmTime = alarmTime

                                viewModel.insertSchedule(
                                    Schedule(
                                        name = scheduleName.value.text,
                                        items = ScheduleItems(itemListOfStrings, timerListOfStrings)
                                    )
                                )
                                navController.navigate(Screens.Schedule.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                    ) {
                        Text(text = "Save Schedule")
                    }

                }
            }


    }


}