package pe.edu.upc.digitalholics.appmobile.ui.srceens.Payment

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Appointment
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.data.remote.ApiClient
import pe.edu.upc.digitalholics.appmobile.data.remote.AppointmentInterface
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure
import pe.edu.upc.digitalholics.appmobile.ui.srceens.Schedule.DateForm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.max


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Payment( modifier: Modifier = Modifier,
    topic: String,
    date: String,
physiotherapist: Physiotherapist,
navController: NavController){



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = modifier.width(8.dp))
                    Row(modifier = modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Payment", fontWeight = FontWeight.Bold)
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
            Column(modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxHeight()
                .padding(bottom = 100.dp)

            ) {
                Payment2(physiotherapist = physiotherapist,
                    topic = topic,
                    date = date,
                    navController = navController)

            }


        },
        bottomBar = {
            Box(modifier = Modifier.background(Color.White)){
                FooterStructure(navController)
            }


        }
    )
}


@Composable
fun Payment2(physiotherapist: Physiotherapist,
    topic: String,
    date: String,navController: NavController) {


    //val videos = 45
    //val appointments = 45
    var showDialog by remember {
        mutableStateOf(
            false
        )
    }

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
    }
    val id = sharedPreferences.getString("userLogged", "0")

    val patient = remember {
        mutableStateOf(
            Patient(
                0,
                0,
                " ",
                " ",
                0,
                " ",
                0,
                " ",
                " "
            )
        )
    }

    val patientsInterface = ApiClient.buildPatientInterface()
    val appointmentInterface = ApiClient.buildAppointmentInterface()
    var patientId = 0

    LaunchedEffect(Unit) {
        val getPatient = patientsInterface.getPatientById(id.toString())


        getPatient.enqueue(object : Callback<Patient> {
            override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                if (response.isSuccessful) {
                    patient.value = response.body()!!
                    patientId = patient.value.userId
                }
            }

            override fun onFailure(call: Call<Patient>, t: Throwable) {
            }
        })
    }

    val errorMessage = remember { mutableStateOf("") }
    val createMessage = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Text("Physiotherapist's Profile", fontWeight = FontWeight.Bold)

        AsyncImage(
            model = physiotherapist.photoUrl,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        /*Image(
            painter = painterResource(R.drawable.physiotherapist),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(230.dp)
                .align(Alignment.CenterHorizontally)
                .clip(shape = CircleShape)
                .border(border = BorderStroke(7.dp, Color(0, 40, 122)), shape = CircleShape)

        )*/
        Text("Dr." + "${physiotherapist.firstName + " " + physiotherapist.paternalSurname}", fontSize = 25.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.absolutePadding(top = 10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            if (physiotherapist.consultationsQuantity > 20) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.absolutePadding(right = 45.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_groups_24),
                        contentDescription = "Appointments",
                        tint = Color(1,72,255),
                        modifier = Modifier
                            .size(48.dp)
                            .absolutePadding(bottom = 5.dp)
                    )
                    Text("20+", color = Color.Gray, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp, left = 6.dp))

                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.absolutePadding(right = 45.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_groups_24),
                        contentDescription = "Appointments",
                        tint = Color(1,72,255),
                        modifier = Modifier
                            .size(48.dp)
                            .absolutePadding(bottom = 5.dp)
                    )
                    Text("${physiotherapist.consultationsQuantity}", color = Color.Gray, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp, left = 6.dp))

                }
            }
            if (physiotherapist.age > 0) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.absolutePadding(right = 45.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_attach_money_24),
                        contentDescription = "Videos",
                        tint = Color(0,120,0),
                        modifier = Modifier
                            .size(36.dp)
                            .absolutePadding(right = 4.dp)
                    )
                    Text("${physiotherapist.age * 2}", color = Color.Gray, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.baseline_star_24),
                    contentDescription = "Stars",
                    tint = Color(250,200,0),
                    modifier = Modifier
                        .size(48.dp)
                        .absolutePadding(bottom = 5.dp)
                )
                Text("${physiotherapist.rating}", color = Color.Gray, fontWeight = FontWeight.Bold,
                    fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))

            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(){
            item{
                CardForm()

                Spacer(modifier = Modifier.height(5.dp))


                Button(onClick = {

                    val newAppointment = Appointment(
                        id = 0,
                        scheduledDate = date,
                        topic = topic,
                        diagnosis = "No yet",
                        done = "0",
                        patient = patient.value  ,
                        physiotherapist = physiotherapist
                    )

                    coroutineScope.launch {

                        try {
                            val response = appointmentInterface.postNewAppointment(newAppointment)
                            if (response.isSuccessful) {
                                createMessage.value = "Se guardó el tratamiento"
                                showDialog = true
                            } else {
                                errorMessage.value =
                                    "Error en la llamada POST. Código de respuesta: ${response.code()}"
                                val errorBody = response.errorBody()?.string()
                                errorBody?.let { errorMessage.value += newAppointment }
                            }
                        } catch (e: Exception) {
                            errorMessage.value = "Excepción durante la llamada POST: ${e.message}"
                        }
                    }


                },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .absolutePadding(left = 130.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0,122,240)
                    )
                ) {
                    Text(text = "Pay",
                    )
                }
                
                Text(text = errorMessage.value)
                
            }
            
            
        }


        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_check_circle_outline_24),
                        contentDescription = "Stars",
                        tint = Color(0,160,0),
                        modifier = Modifier
                            .size(140.dp)
                            //.absolutePadding(bottom = 5.dp)
                    )
                },
                title = {
                    Text(text = "Successful Payment")
                },

                confirmButton = {
                    Button(colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0,122,240)
                    ), modifier = Modifier.absolutePadding(right = 60.dp)
                        ,onClick = { showDialog = false

                    navController.navigate("physiotherapistList")}) {
                        Text(text = "Go Back")
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }

    }

}

@Composable
fun Footer(){
    Spacer(modifier = Modifier.padding(4.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.medium
            ),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick =  {  }) {
                Icon(imageVector = Icons.Default.List, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForm(){
    var cardHolder  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }

    var cardNumber  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }

    var cardExpiration  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }

    var cvv  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }


    Row(modifier = Modifier) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text("Card Number") },
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(bottom = 10.dp)
        )
    }

    Row(modifier = Modifier) {
        OutlinedTextField(
            value = cardHolder,
            onValueChange = { cardHolder = it },
            label = { Text("Card Holder") },
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(bottom = 10.dp)
        )
    }

    Row(modifier = Modifier) {
        OutlinedTextField(
            value = cardExpiration,
            onValueChange = { cardExpiration = it },
            label = { Text("Card Expiration") },
            modifier = Modifier
                .absolutePadding(bottom = 10.dp, right = 10.dp)
        )

        OutlinedTextField(
            value = cvv,
            onValueChange = { cvv = it },
            label = { Text("CVV") },
            modifier = Modifier
                .absolutePadding(bottom = 10.dp)
        )
    }
}

@Composable
fun StyledAlertDialog(
    bodyText: String,
    buttonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(bodyText)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismiss()
                },
                colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
            ) {
                Text(text = buttonText)
            }
        },
        shape = RoundedCornerShape(
            topEndPercent = 50,
            bottomStartPercent = 50
        ),
    )
}
