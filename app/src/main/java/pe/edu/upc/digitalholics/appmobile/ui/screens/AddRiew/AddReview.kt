package pe.edu.upc.digitalholics.appmobile.ui.screens.AddRiew

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.data.model.Review
import pe.edu.upc.digitalholics.appmobile.data.remote.ApiClient
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddReview(physiotherapist: Physiotherapist, reviews: List<Review>, navController: NavController, modifier: Modifier = Modifier) {

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

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
    }
    val id = sharedPreferences.getString("userLogged", "0")

    val getPatient = patientsInterface.getPatientById(id.toString())


    getPatient.enqueue(object : Callback<Patient> {
        override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
            if (response.isSuccessful) {
                patient.value = response.body()!!
            }
        }

        override fun onFailure(call: Call<Patient>, t: Throwable) {
        }
    })



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = modifier.width(8.dp))
                    Row(modifier = modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Add Review", fontWeight = FontWeight.Bold)
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
            LazyColumn(modifier = Modifier.padding(top = 70.dp)
                .fillMaxHeight()
                .padding(bottom = 100.dp)
            ) {
                item {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {

                        AsyncImage(
                            model = physiotherapist.photoUrl,
                            contentDescription = null,
                            modifier = modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            "Dr. " + physiotherapist.firstName+ " "+ physiotherapist.paternalSurname, fontSize = 25.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.absolutePadding(top = 10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            if (physiotherapist.age > 20) {
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
                                    Text(
                                        physiotherapist.consultationsQuantity.toString(), color = Color.Gray, fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp, left = 6.dp))

                                }
                            }

                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.absolutePadding(right = 45.dp)) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_attach_money_24),
                                    contentDescription = "Cost",
                                    tint = Color(64, 131, 64, 255),
                                    modifier = Modifier
                                        .size(36.dp)
                                        .absolutePadding(right = 4.dp)
                                )
                                Text("${physiotherapist.age*2}", color = Color.Gray, fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))
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
                                Text(
                                    physiotherapist.rating.toString(), color = Color.Gray, fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp, modifier = Modifier.absolutePadding(bottom = 5.dp))

                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        NewReview(patient.value, physiotherapist,reviews, navController)


                    }
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier.background(Color.White)){
                FooterStructure(navController)
            }


        }
    )






        }


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReview(patient: Patient, physiotherapist: Physiotherapist,reviews: List<Review>,
navController: NavController){

    val errorMessage = remember { mutableStateOf("") }
    val createMessage = remember { mutableStateOf("") }

    var reviewContent by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    var selectedStars by remember { mutableStateOf(0) }

    //Obtencion
    val reviewsInterface = ApiClient.buildReviewInterface()
    val coroutineScope = rememberCoroutineScope()






    LaunchedEffect(Unit) {




    }


    Text(
        text = "Review ${patient.firstName}",
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = Modifier
            .absolutePadding(bottom = 15.dp)

    )

    Row(modifier = Modifier) {
        OutlinedTextField(
            value = reviewContent,
            onValueChange = { reviewContent = it },
            modifier = Modifier
                .fillMaxWidth()
                .absolutePadding(bottom = 10.dp)
                .size(width = 200.dp, height =100.dp)
        )
    }
    Spacer(modifier = Modifier.height(5.dp))

    Row {
        repeat(5) { index ->
            Image(
                painter = if (index < selectedStars) {
                    painterResource(R.drawable.baseline_star_24)
                } else {
                    painterResource(R.drawable.baseline_star_outline_24)
                },

                contentDescription = null, // Descripción opcional para accesibilidad
                modifier = Modifier
                    .size(24.dp)
                    .clickable { selectedStars = index + 1 }

            )
        }

        Spacer(modifier = Modifier.width(200.dp))
        Text(
            text = "$selectedStars / 5",
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(top = 8.dp)
        )
    }

    Spacer(modifier = Modifier.height(5.dp))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column( modifier = Modifier.align(Alignment.Center),){
            Button(
                onClick = {
                    val newReviewId = reviews.size + 1

                    val newReview = Review(
                        newReviewId,
                        reviewContent.text,
                        selectedStars,
                        patient,
                        physiotherapist
                    )

                    coroutineScope.launch {

                        try {
                            val response = reviewsInterface.postNewReview(newReview)
                            if (response.isSuccessful) {
                                createMessage.value = "Se guardó el tratamiento"
                                navController.popBackStack()
                            } else {
                                errorMessage.value =
                                    "Error en la llamada POST. Código de respuesta: ${response.code()}"
                                val errorBody = response.errorBody()?.string()
                                errorBody?.let { errorMessage.value += newReview }
                            }
                        } catch (e: Exception) {
                            errorMessage.value = "Excepción durante la llamada POST: ${e.message}"
                        }
                    }
                    //navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 122, 240)
                )
            ) {
                Text(text = "Save")
            }

            Spacer(modifier = Modifier.height(15.dp))

            ReviewStatusMessage(createMessage = createMessage.value, errorMessage = errorMessage.value)

        }
        }
    }


@Composable
fun ReviewStatusMessage(createMessage: String, errorMessage: String) {
    if (createMessage.isNotEmpty()) {
        Text(text = createMessage, color = Color.Green)
    }
    if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, color = Color.Red)
    }
}


