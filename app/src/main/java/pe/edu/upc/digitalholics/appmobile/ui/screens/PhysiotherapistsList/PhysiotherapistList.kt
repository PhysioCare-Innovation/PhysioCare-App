package pe.edu.upc.digitalholics.appmobile.ui.screens.PhysiotherapistsList

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysiotherapistList(navController: NavController,physiotherapists: List<Physiotherapist>){
    var pacientutos: MutableList<Physiotherapist> = physiotherapists.toMutableList()
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
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(modifier = Modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Find your physiotherapist", fontWeight = FontWeight.Bold)
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
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
                    Column(modifier = Modifier.padding(17.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            contentAlignment = Alignment.CenterEnd, modifier = Modifier
                                .absolutePadding(bottom = 25.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .border(
                                    BorderStroke(1.dp, Color.Magenta)
                                )
                                .fillMaxWidth()
                        ) {

                            TextField(value = searchedText, onValueChange = { searchedText = it }, Modifier.fillMaxWidth())
                            IconButton(onClick = {
                                //searchedText = text
                                //pacientutos = patients.subList(0,1).toMutableList()
                            }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                            }



                        }

                    }

                }

            }
            LazyColumn(Modifier.height(660.dp)
                .absolutePadding(top = 150.dp, left = 22.dp)
                .width(350.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                itemsIndexed(pacientutos){ index, item ->
                    PhysiotherapistItem(navController,item, searchedText.text
                    )
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

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhysiotherapistItem(navController: NavController,physiotherapist: Physiotherapist,  searchedText:String){
    if(physiotherapist.firstName.uppercase().contains(searchedText.uppercase()) || physiotherapist.paternalSurname.uppercase().contains(searchedText.uppercase()))
        Row() {
            Card(onClick = {
                navController.navigate(
                    "physiotherapistProfile/${physiotherapist.id}"
                )
            },
                modifier = Modifier
                    .absolutePadding(top = 15.dp, bottom = 15.dp)
                    .fillMaxWidth()

            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0, 122, 240))
                        .fillMaxWidth()
                        .size(100.dp)) {

                    AsyncImage(
                        model = physiotherapist.photoUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .absolutePadding(left = 10.dp)
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )




                    Column(modifier = Modifier.absolutePadding(left = 10.dp)) {
                        Text(text = physiotherapist.firstName + " " + physiotherapist.paternalSurname, fontWeight = FontWeight.Bold,
                            modifier = Modifier.absolutePadding( bottom = 15.dp), color = Color.White)
                        Row() {
                            Box(contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(25.dp)
                                    .absolutePadding(right = 8.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                            ) {

                                FiveStarRating(physiotherapist)
                                /*Text(text = "Stars: " + physiotherapist.rating +"/5", fontSize = 10.sp,
                                    modifier = Modifier.background(Color(255,255,255)))*/
                            }
                            /*Box(contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(200.dp)
                                    .absolutePadding(right = 10.dp)
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .background(Color(255, 255, 255))
                            ) {
                                Text(text = "Appointment Consultations: " + physiotherapist.consultationsQuantity, fontSize = 10.sp)
                            }*/
                        }

                    }

                }

            }


        }
}


@Composable
fun FiveStarRating(physiotherapist: Physiotherapist) {
    Row {
        repeat(physiotherapist.rating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null, // Descripción opcional para accesibilidad
                tint = Color.Yellow,
                modifier = Modifier.size(24.dp)
            )
        }
        repeat(5-physiotherapist.rating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null, // Descripción opcional para accesibilidad
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

