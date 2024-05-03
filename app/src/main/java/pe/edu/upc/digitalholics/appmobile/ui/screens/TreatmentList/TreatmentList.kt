package pe.edu.upc.digitalholics.appmobile.ui.screens.TreatmentList

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Treatments(treatments: List<Treatment>, selectTreatment: (String) -> Unit,navController: NavController){
    var searchedText  by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }

    val treat = remember {
        mutableStateOf(listOf<Treatment>())
    }
    treat.value = treatments

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(modifier = Modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Treatments", fontWeight = FontWeight.Bold)
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

                        Box(
                            contentAlignment = Alignment.CenterEnd, modifier = Modifier
                                .absolutePadding(bottom = 25.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .border(
                                    BorderStroke(1.dp, Color.Magenta)
                                )
                                .fillMaxWidth()
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


                    }


                }



           }
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(top = 70.dp).width(385.dp)
                    .padding(bottom = 100.dp).absolutePadding(top = 110.dp, right = 9.dp)) {
                items(treat.value) {
                    MyTreatments(it, searchedText.text, navController)
                }
            }
        },
        bottomBar = {
            Box(modifier = Modifier.background(Color.White)){
                FooterStructure(navController)
            }


        }
    )


    /*LazyColumn(){
        itemsIndexed(treatments){index, item ->
            TreatmentItem(item,{
                selectTreatment("${index+1}")
            },navController)
        }
    }*/

}



@Composable
fun MyTreatments(treatments: Treatment, searchedText: String, navController: NavController){

    Surface(
        modifier = Modifier
            .padding(start = 16.dp),
        shape = MaterialTheme.shapes.medium,
        color = Color.Transparent
    ) {
        if(treatments.title.uppercase().contains(searchedText.uppercase())) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Color(132, 170, 255, 255))
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = treatments.photoUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(92.dp)
                            .padding(top = 7.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .border(BorderStroke(5.dp, Color.White)),
                        contentScale = ContentScale.Crop
                    )

                    Text(text = treatments.title, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity Sessions: ${treatments.sessionsQuantity}")
                    Button(onClick = { navController.navigate("treatment/${treatments.id}")}, modifier = Modifier.padding(6.dp)) {
                        Text(text = "Info")
                    }
                }
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TreatmentItem(treatment: Treatment, selectTreatment: () -> Unit,navController: NavController){
    Card(
        onClick = {

        }) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Find your treatment", fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(10.dp))
            Row(){
                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .border(
                        BorderStroke(1.dp, Color.Magenta)
                    )){
                    TextField(value ="" , onValueChange = {})
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            }
//        Spacer(modifier = Modifier.height(20.dp))
            Row() {
                Card(modifier = Modifier.padding(3.dp)) {
                    //image
                    Image(painter = painterResource(id = R.drawable.lumbar),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(3.dp)
                    )
//                Spacer(modifier = Modifier.height(80.dp))
                    Text(text = treatment.title, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity Sessions: ${treatment.sessionsQuantity}")
                    Button(onClick = { selectTreatment() }, modifier = Modifier.padding(6.dp)) {
                        Text(text = "Info")
                    }
                }
                Card(modifier = Modifier.padding(3.dp)) {
                    //image
                    Image(painter = painterResource(id = R.drawable.lumbar),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(3.dp)
                    )
//                Spacer(modifier = Modifier.height(80.dp))
                    Text(text = treatment.title, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity Sessions: ${treatment.sessionsQuantity}")
                    Button(onClick = { selectTreatment() }, modifier = Modifier.padding(6.dp)) {
                        Text(text = "Info")
                    }
                }
            }
            Row() {
                Card(modifier = Modifier.padding(3.dp)) {
                    //image
                    Image(painter = painterResource(id = R.drawable.lumbar),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(3.dp)
                    )
//                Spacer(modifier = Modifier.height(80.dp))
                    Text(text = treatment.title, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity Sessions: ${treatment.sessionsQuantity}")
                    Button(onClick = { selectTreatment() }, modifier = Modifier.padding(6.dp)) {
                        Text(text = "Info")
                    }
                }
                Card(modifier = Modifier.padding(3.dp)) {
                    //image
                    Image(painter = painterResource(id = R.drawable.lumbar),
                        contentDescription = null,
                        modifier = Modifier
                            .width(170.dp)
                            .height(170.dp)
                            .padding(3.dp)
                    )
//                Spacer(modifier = Modifier.height(80.dp))
                    Text(text = treatment.title, fontWeight = FontWeight.Bold)
                    Text(text = "Quantity Sessions: ${treatment.sessionsQuantity}")
                    Button(onClick = { selectTreatment() }, modifier = Modifier.padding(6.dp)) {
                        Text(text = "Info")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))


//        Spacer(modifier = Modifier.height(90.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(3.dp, Color.Magenta),contentAlignment = Alignment.Center
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.List, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    val a= 1
                    IconButton(onClick = {  navController.navigate("patient/$a")}) {
                        Icon(imageVector = Icons.Default.Face, contentDescription = null)
                    }
                }
            }
        }
    }
}