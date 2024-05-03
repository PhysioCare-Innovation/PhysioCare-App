package pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import androidx.compose.material3.Icon
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
fun ForgotPasswordScream(users: List<User>, navController: NavController){

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channel= "default_channel"
    val channelName = "Default Channel"
    var userId by remember { mutableStateOf("") }

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
                text = "Forgot password?",
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
                text = "Please enter the email associated with your\n" +
                        "account.",
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

            var email by remember { mutableStateOf(TextFieldValue("")) }
            var randomNumber by remember { mutableStateOf(0) }
            val emailRegex = Regex("[^\\n]+")
            val maxEmailLength = 30

            OutlinedTextField(
                value = email,
                onValueChange = { newValue ->
                    if (newValue.text.matches(emailRegex) && newValue.text.length <= maxEmailLength) {
                        email = newValue
                    }},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Button(
                onClick = {
                    if (email.text.isEmpty()) {
                        showDialog=true
                    } else{
                        users.forEach{
                            if(email.text == it.email){
                                randomNumber = Random.nextInt(1000, 9999)
                                userId = it.id.toString()
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    val importance = NotificationManager.IMPORTANCE_HIGH
                                    val channel = NotificationChannel(channel, channelName, importance)
                                    notificationManager.createNotificationChannel(channel)
                                }
                                val builder = NotificationCompat.Builder(context, channel)
                                    .setContentTitle("Código de recuperación")
                                    .setContentText("Tu código de recuperación es: $randomNumber")
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setAutoCancel(true)
                                    .setSmallIcon(R.drawable.branding)


                                notificationManager.notify(1, builder.build())

                                navController.navigate("EnterCodeView/$userId/$randomNumber")
                            }
                        }
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
                Text(text = "I do not register or the account registered with the email provided does not exist")
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
