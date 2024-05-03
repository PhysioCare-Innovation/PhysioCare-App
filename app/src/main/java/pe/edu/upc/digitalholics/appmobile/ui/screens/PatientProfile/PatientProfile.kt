package pe.edu.upc.digitalholics.appmobile.ui.screens.PatientProfile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


import coil.compose.AsyncImage

import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure
import pe.edu.upc.digitalholics.appmobile.ui.screens.TreatmentDetails.Footer
import kotlin.random.Random


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientProfile(patient: Patient, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    PatientProfileTitle()
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    item {
                        PatientProfilelImage(patient = patient)
                    }
                    item {
                        PatientProfileDetails(patient = patient, navController = navController)
                    }
                }
            }
        },
        bottomBar = {
            FooterStructure(navController)
        }
    )
}

@Composable
fun PatientProfileTitle(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(8.dp))
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(7f)) {
            Text(text = "Patient Profile", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PatientProfilelImage(
   patient: Patient,
    modifier: Modifier = Modifier
) {
    Row {
        Column(
            modifier = Modifier
                .padding(100.dp, 80.dp, 0.dp, 0.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            AsyncImage(
                model = patient.photoUrl,
                contentDescription = null,
                modifier = modifier
                    .size(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
@Composable
fun PatientProfileDetails(
    patient: Patient,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Spacer(modifier = modifier.height(20.dp))
    Row(modifier = modifier) {
        Spacer(modifier = modifier.width(2.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = patient.firstName + " " + patient.lastName,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    Spacer(modifier = modifier.height(20.dp))
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.alignByBaseline()) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(66.dp))
                    val random = Random(System.currentTimeMillis())

                    val min = 10000000 // El número mínimo de 8 dígitos (10^7)
                    val max = 99999999 // El número máximo de 8 dígitos (10^8 - 1)

                    val randomNumber = random.nextInt(min, max + 1)

                    Text(
                        text = "9$randomNumber",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alignByBaseline()
                    )
                }


            Spacer(modifier = modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.alignByBaseline()) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = modifier.width(15.dp))
                Text(
                    text = patient.email,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alignByBaseline()
                )
            }

            Spacer(modifier = modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.alignByBaseline()) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = modifier.width(61.dp))
                Text(
                    text = patient.birthdayDate,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
    }
    Spacer(modifier = modifier.height(30.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /* acción al hacer clic en el botón */ }) {
            Text(text = "Medical History")
        }
        Button(onClick = { navController.navigate("LoginView") }) {
            Text(text = "Log Out")
        }
    }
}