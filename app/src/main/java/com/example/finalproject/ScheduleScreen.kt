package com.example.finalproject

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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

//    Column(Modifier.fillMaxHeight()) {
//
//        Row(Modifier.fillMaxWidth()) {
//            Text(text = "Your Schedules", modifier = Modifier
//                .padding(all = 20.dp)
//                .padding(top = 15.dp))
//            Spacer(modifier = Modifier.width(140.dp))
//            Button(onClick = {navController.navigate(route = Screens.Edit.route)}) {
//                Text(text = "edit ", modifier = Modifier.padding(all = 20.dp))
//            }
//        }
//        val EntriesPerPage = 3
//        var schedNumStart by remember{
//            mutableStateOf(0)
//        }
//        var schedNumEnd by remember{
//            mutableStateOf(EntriesPerPage-1)
//        }
//        for(i in schedNumStart..minOf(schedNumEnd, schedules.size-1)){
//            Button(onClick = {navController.navigate(route= Screens.Checklist.route) },modifier = Modifier
//                .fillMaxWidth()
//                .padding(all = 10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
//                Text(
//                    text = schedules[i].name,
//                    Modifier
//                        .padding(all = 20.dp)
//                        .padding(top = 20.dp)
//                )
//            }
//        }
////        schedules.forEach{ scheduleName ->
////            Button(onClick = {/*placeholder*/ },modifier = Modifier.fillMaxWidth().padding(all=10.dp) ,colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)) {
////                Text(text = scheduleName.firstProperty, Modifier.padding(all = 20.dp).padding(top = 20.dp))
////            }
////        }
//        var pageNum by remember{
//            mutableStateOf(1)
//        }
//        var pages by remember{
//            mutableStateOf((schedules.size / EntriesPerPage))
//        }
//        val totalPageNum by remember{
//
//
//            if (schedules.size % EntriesPerPage != 0 && schedules.size > EntriesPerPage) {
//                pages = (schedules.size / EntriesPerPage) + 1
//            }
//            mutableStateOf(pages)
//        }
//
//        Row(Modifier.fillMaxWidth()) {
//            Text(text = "Page $pageNum out of $totalPageNum", modifier = Modifier
//                .padding(all = 20.dp)
//                .padding(top = 15.dp))
//
//        }
//        Row(Modifier.fillMaxWidth()) {
//            Button(onClick = {if(pageNum>1) {
//                pageNum--
//                schedNumEnd -= EntriesPerPage
//                if(schedNumStart>0)schedNumStart -= EntriesPerPage
//            }}) {
//                Text(text = "Previous", modifier = Modifier.padding(all = 20.dp))
//            }
//            Button(onClick = {
//                Log.e("myTag", "This is my message")
//                if(pageNum<totalPageNum) {
//                pageNum++
//                schedNumStart += EntriesPerPage
//                if(schedNumEnd<schedules.size)schedNumEnd += EntriesPerPage
//            }}) {
//                Text(text = "Next", modifier = Modifier.padding(all = 20.dp))
//            }
//        }
//    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f)
        )
        Text(
            head3, color = Color.White,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun ScheduleRow(id: Int, name: String, quantity: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(name, modifier = Modifier.weight(0.2f))
        Text(quantity.toString(), modifier = Modifier.weight(0.2f))
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