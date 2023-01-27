package com.example.finalproject

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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
    val alarmList: SnapshotStateList<String> = if (schedule != null) {
        remember {schedule.items.scheduleAlarms }
    }else{
        remember {SnapshotStateList() }
    }

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

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
                for (i in 0 until itemList.size) {

                    val mTimePickerDialog = TimePickerDialog(
                        context,
                        {_, mHour : Int, mMinute: Int ->
                            alarmList[i] = "$mHour:$mMinute"
                        }, mHour, mMinute, false
                    )

                    Text("Item #${i + 1}", modifier = Modifier.padding(all = 10.dp))
                    Row {
                        TextField(
                            value = itemList[i],
                            onValueChange = { itemList[i] = it },
                            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor =  colorResource(R.color.SubGreen), cursorColor = colorResource(R.color.SubGreen), placeholderColor = colorResource(R.color.SubGreen), trailingIconColor = colorResource(R.color.SubGreen))
                        )
                        Button(onClick = { itemList.removeAt(i); alarmList.removeAt(i) }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                            Text("Delete")
                        }
                    }
                    Button(onClick = { mTimePickerDialog.show() }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
                        Text(text = "Open Time Picker", color = Color.White)
                    }
                }
            }
            Button(onClick = {itemList.add(""); alarmList.add("")}, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))){
                Text("Add Item")
            }
        }
        Column{
            Row {
                Button(onClick = {
                    if (schedule != null) {
                        schedule.items = ScheduleItems(itemList, alarmList)
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
