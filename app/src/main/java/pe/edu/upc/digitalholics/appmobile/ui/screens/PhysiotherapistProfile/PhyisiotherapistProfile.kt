package pe.edu.upc.digitalholics.appmobile.ui.screens.PhysiotherapistProfile

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.data.model.Review
import pe.edu.upc.digitalholics.appmobile.ui.screens.FooterStructure
import java.util.concurrent.ScheduledExecutorService

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhysiotherapistProfile(physiotherapist: Physiotherapist, reviews: List<Review>,navController: NavController, modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = modifier.width(8.dp))
                    Row(modifier = modifier) {
                        Column(modifier = Modifier.weight(7f)) {
                            Text(text = "Physiotherapist's profile", fontWeight = FontWeight.Bold)
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
                .padding(bottom = 100.dp)) {

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
                        Spacer(modifier = Modifier.height(1.dp))

                        Reviews(physiotherapist,reviews)

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(onClick = {
                                navController.navigate("addReview/${physiotherapist.id}")
                            },
                                modifier = Modifier
                                    .width(130.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0,122,240)
                                )
                            ) {
                                Text(text = "Add Review")

                            }
                            Spacer(modifier = Modifier.width(25.dp))
                            Button(onClick = {
                                navController.navigate("Schedule/${physiotherapist.id}")
                            },
                                modifier = Modifier
                                    .width(170.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0,122,240)
                                )
                            ) {
                                Text(text = "Make Appointment")

                            }

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reviews(physiotherapist: Physiotherapist,reviews: List<Review>){
    Text(text = "Reviews",fontWeight = FontWeight.Bold,
        fontSize = 25.sp)

    Spacer(modifier = Modifier.height(10.dp))
    LazyColumn(modifier = Modifier.height(170.dp)) {
        item {
            reviews.forEach { review ->
                if (review.physiotherapist.id == physiotherapist.id) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(
                            model = review.patient.photoUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Row() {
                                Text(
                                    text = review.patient.firstName + " " + review.patient.lastName,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Spacer(modifier = Modifier.width(150.dp))


                                Text(
                                    text = "${review.stars}", fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Icon(
                                    painter = painterResource(R.drawable.baseline_star_24),
                                    contentDescription = "Stars",
                                    tint = Color(250, 200, 0),
                                    modifier = Modifier
                                        .size(24.dp)
                                        .absolutePadding(bottom = 5.dp)
                                )
                            }

                            Text(text = review.description)

                        }

                    }

                }

            }
        }
    }

}



