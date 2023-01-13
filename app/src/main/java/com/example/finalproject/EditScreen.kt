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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EditScreen(
    schedules: List<Schedule>,
    navController: NavController,
    viewModel :MainViewModel
) {
    Column(
        Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .background(colorResource(R.color.Background))
    ) {

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                text = "Edit Your Schedules", modifier = Modifier
                    .padding(all = 10.dp)
                    .weight(1f,false),
                fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Button(
                onClick = { navController.navigate(route = Screens.Schedule.route) },
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.TitleGreen))
            ) {
                Text(text = "Done", modifier = Modifier.padding(all = 20.dp))
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        for (schedule in schedules) {
            Row {
                Button(
                    onClick = { navController.navigate("${Screens.EditSchedule.route}/${schedule.id}") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(all = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
                ) {
                    Text(
                        text = schedule.name,
                        Modifier
                            .padding(all = 20.dp)
                            .padding(top = 20.dp)
                    )
                }
                Button(
                    onClick = { viewModel.deleteSchedule(schedule.id.toInt()) },
                    modifier = Modifier.width(100.dp).padding(vertical = 30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Delete Schedule")
                }
            }
        }



        Button(
            onClick = {

                navController.navigate(route = Screens.Create.route)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.SubGreen))
        ) {

            Text(
                text = "Create Schedule",
                Modifier
                    .padding(all = 20.dp)
                    .padding(top = 20.dp)
            )
        }
    }
}
