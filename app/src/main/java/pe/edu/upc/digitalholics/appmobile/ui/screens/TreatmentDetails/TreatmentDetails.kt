package pe.edu.upc.digitalholics.appmobile.ui.screens.TreatmentDetails

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.TreatmentByPatient
import pe.edu.upc.digitalholics.appmobile.data.remote.ApiClient
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure

@Composable
fun Treatments(treatments: List<Treatment>){
    LazyColumn(){
        itemsIndexed(treatments){ index, item ->
            //TreatmentDetails(item)
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreatmentDetails(treatment: Treatment, navController: NavController, enrolled: Boolean) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TreatmentTitle(treatment = treatment)
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
                        TreatmentDetailImage(treatment = treatment)
                    }
                    item {
                        TreatmentDescription(treatment = treatment, navController = navController, enrolled = enrolled)
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
fun TreatmentTitle(
    treatment: Treatment,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(8.dp))
    Row(modifier = modifier) {
        Column(modifier = Modifier.weight(7f)) {
            Text(text = treatment.title, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TreatmentDetailImage(
    treatment: Treatment,
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
                model = treatment.photoUrl,
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
fun TreatmentDescription(treatment: Treatment, modifier: Modifier = Modifier, navController: NavController, enrolled: Boolean) {

    val treatmentByPatientInterface = ApiClient.buildTreatmentByPatientInterface()

    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
    }
    val id = sharedPreferences.getString("userLogged", "0")
    val coroutineScope = rememberCoroutineScope()
    val errorMessage = remember { mutableStateOf("") }
    val createMessage = remember { mutableStateOf("") }
    Spacer(modifier = modifier.height(20.dp))
    Row(modifier = modifier) {
        Spacer(modifier = modifier.width(28.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Description ", fontWeight = FontWeight.Bold)
        }
    }

    Spacer(modifier = modifier.height(15.dp))
    Row(modifier = modifier) {
        Spacer(modifier = modifier.width(28.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = treatment.description, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = modifier.width(28.dp))
    }

    Spacer(modifier = modifier.height(15.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = !enrolled,
            onClick = {

            val newTreatmentByPatientId = "0"
            val patient = Patient(
                id!!.toInt(),
                0,
                " ",
                "20",
                0,
                "jose@gmail.com",
                0,
                "https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg",
                ""
            )

            val newTreatment = TreatmentByPatient(
                newTreatmentByPatientId,
                treatment,
                patient,
                "2023-07-01",
                0.0

            )

            coroutineScope.launch {

                try {
                    val response = treatmentByPatientInterface.postNewTreatmentByPatient(newTreatment)
                    if (response.isSuccessful) {
                        createMessage.value = "Se guardó el tratamiento"
                        navController.popBackStack()
                    } else {
                        errorMessage.value =
                            "Error en la llamada POST. Código de respuesta: ${response.code()}"
                        val errorBody = response.errorBody()?.string()
                        errorBody?.let { errorMessage.value += newTreatment }
                    }
                } catch (e: Exception) {
                    errorMessage.value = "Excepción durante la llamada POST: ${e.message}"
                }
            }

        /* acción al hacer clic en el botón */
        }


        ) {
            if(!enrolled){
                Text(text = "Enroll")
            }else{
                Text(text = "Already added treatment")
            }
        }
    }
}
@Composable
fun Footer(){
    Spacer(modifier = Modifier.padding(4.dp))
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = MaterialTheme.shapes.medium
            ),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick =  { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.List, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Face, contentDescription = null)
            }
        }
    }

}


