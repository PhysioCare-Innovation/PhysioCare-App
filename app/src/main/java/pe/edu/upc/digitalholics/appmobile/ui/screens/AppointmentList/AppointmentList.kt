package pe.edu.upc.digitalholics.appmobile.ui.srceens.AppointmentList

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ToggleButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Appointment
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentList( modifier: Modifier = Modifier,
                     appointments: List<Appointment>,
navController: NavController){

    var dona  by remember {
        mutableStateOf(
            String()
            //TextFieldValue(
            //  text = "0"
            //)
        )
    }

    var searchedText  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = modifier.width(8.dp))
                    Row(modifier = modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "My Appointments", fontWeight = FontWeight.Bold)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            LazyColumn(modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxHeight()
                .padding(bottom = 100.dp)
            ) {
                item {
                    Column(modifier = Modifier.padding(17.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            Box(
                                contentAlignment = Alignment.CenterEnd, modifier = Modifier
                                    .absolutePadding(bottom = 25.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .border(
                                        BorderStroke(1.dp, Color.Magenta)
                                    ).width(240.dp)
                            ) {

                                TextField(
                                    value = searchedText,
                                    onValueChange = { searchedText = it },
                                    Modifier.fillMaxWidth()
                                )
                                IconButton(onClick = {
                                    //searchedText = text
                                    //pacientutos = patients.subList(0,1).toMutableList()
                                }) {
                                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                                }

                            }
                            dona = ToggleButtonExample()

                        }



                    }

                }

            }
            AppointmentList2(appointments, searchedText = searchedText.text, dona = dona)


        },
        bottomBar = {
            Box(modifier = Modifier.background(Color.White)){
                FooterStructure(navController)
            }


        }
    )
}

@Composable
fun ToggleButtonExample(): String {
    var isChecked = remember { mutableStateOf(false) }

    var dona  by remember {
        mutableStateOf(
            String()
            //TextFieldValue(
              //  text = "0"
            //)
        )
    }


    Row(modifier = Modifier.absolutePadding(left = 20.dp) ) {
        Text(text = "Done", modifier = Modifier.absolutePadding(top = 12.dp))

        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it
                              if(isChecked.value){
                                  dona ="1"
                              }else{
                                  dona = "0"
                              }},
            modifier = Modifier.absolutePadding(left = 10.dp,bottom = 50.dp)
        )
    }
    if(dona=="")
        return "0"
    return dona
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentList2(appointments: List<Appointment>, searchedText: String, dona: String){
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
    }

    LazyColumn(
        Modifier
            .height(660.dp)
            .absolutePadding(top = 150.dp, left = 22.dp)
            .width(350.dp),
    horizontalAlignment = Alignment.CenterHorizontally){
        itemsIndexed(appointments){ index, item ->
            if((item.physiotherapist.firstName.uppercase().contains(searchedText.uppercase())
            || item.physiotherapist.paternalSurname.uppercase().contains(searchedText.uppercase())
                        ) && item.patient.id.toString() == sharedPreferences.getString("userLogged", "0")
                && item.done == dona
            ) {
                AppointmentItem(item) {
                }
            }
        }
    }

}


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppointmentItem(appointment: Appointment, selectAppointment: () -> Unit) {
    var name = " "
    //var patient = appointment.patient.to(Patient)
   /* for(patient in patients){
        //Text(text = appointment.physiotherapistId)
        //Text(text = appointment.patient)
        if(patient.id == appointment.patient)
            name = patient.firstName + " " + patient.lastName
    }*/
    

    Row() {
        Card(modifier = Modifier
            .absolutePadding(top = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color(212, 98, 155))
                    .fillMaxWidth()) {
                /*Image(painter = painterResource(id = R.drawable.ghost),
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .height(130.dp)
                        .padding(20.dp)
                        .border(BorderStroke(5.dp, Color.White))
                )*/
                AsyncImage(
                    model = appointment.physiotherapist.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(130.dp)
                        .height(130.dp)
                        .padding(20.dp)
                        .border(BorderStroke(5.dp, Color.White)),
                    contentScale = ContentScale.Crop
                )
                Column() {
                    Text(text = appointment.physiotherapist.firstName + " " +appointment.physiotherapist.paternalSurname, fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.absolutePadding(bottom = 15.dp))
                    Row() {
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(25.dp)
                                .width(65.dp)
                                .absolutePadding(right = 8.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Color(255, 255, 255))
                        ) {
                            Text(text = appointment.topic, fontSize = 10.sp,
                                modifier = Modifier.background(Color(255,255,255)))
                        }
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(25.dp)
                                .width(90.dp)
                                .absolutePadding(right = 10.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Color(255, 255, 255))
                        ) {
                            Text(text = appointment.scheduledDate, fontSize = 10.sp)

                        }

                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(25.dp)
                                .width(60.dp)
                                .absolutePadding(right = 10.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Color(255, 255, 255))
                        ) {
                            Text(text = "4:00 pm", fontSize = 10.sp)
                        }
                    }

                }

            }

        }
    }

}