package pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.User
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterCodeScream(users: List<User>,userId: String?, randomNumber: Int, navController: NavController){

    var showDialog by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.backgrund),
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp)
            )

            Text(
                text = "Enter code",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(29, 35, 102, 500)
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, end = 12.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))

            Text(
                text = "An 4 digit code has been sent to your email.",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color(29, 35, 102, 500)
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, end = 12.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))

            var code by remember { mutableStateOf(TextFieldValue("")) }
            val emailRegex = Regex("[^\\n]+")
            val maxEmailLength = 4

            OutlinedTextField(
                value = code,
                onValueChange = { newValue ->
                    if (newValue.text.matches(emailRegex) && newValue.text.length <= maxEmailLength) {
                        code = newValue
                    }},
                label = { Text("Code") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Button(
                onClick = {
                    if (code.text.isEmpty()) {
                        showDialog=true
                    }else if (code.text == randomNumber.toString()){
                        showPassword=true
                    }else{
                        showDialog=true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Submit")
            }

        }
    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "The code entered is not correct\n")
            },
            confirmButton = {
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0,122,240)
                ), modifier = Modifier.absolutePadding(right = 60.dp)
                    ,onClick = { showDialog = false
                        navController.navigate("LoginView")}) {
                    Text(text = "Back to Loging")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }

    if (showPassword){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title ={
                users.forEach{
                    if(userId == it.id.toString()){
                        Text(text = "Your password is: ${it.password}")
                    }
                }
            },
            confirmButton = {
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0,122,240)
                ), modifier = Modifier.absolutePadding(right = 60.dp)
                    ,onClick = { showDialog = false
                        navController.navigate("LoginView")}) {
                    Text(text = "Back to Loging")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}