package pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.User
import pe.edu.upc.digitalholics.appmobile.ui.srceens.Schedule.replace

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(users: List<User>, navController: NavController) {
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.branding),
                contentDescription = "Logo",
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = "Login",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, end = 12.dp)
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            var email by remember { mutableStateOf(TextFieldValue("")) }
            var password by remember { mutableStateOf(TextFieldValue("")) }
            val passwordVisibility = remember { mutableStateOf(false) }
            val errorState = remember { mutableStateOf(false) }
            val emailRegex = Regex("[^\\n]+")
            val maxPasswordLength = 15
            val maxEmailLength = 30

            OutlinedTextField(
                value = email,
                onValueChange = { newValue ->
//                    if (newValue.text.matches(emailRegex) && newValue.text.length <= maxEmailLength) {
//                        email = newValue
//                    }
                    if ( newValue.text.contains("\n") && newValue.text != "" ) {
                        email =  TextFieldValue(newValue.text.replace("\n", ""))
                    } else {
                        if(newValue.text.length <= maxEmailLength)
                            email = newValue
                    }
                                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { newValue ->
//                    if (newValue.text.matches(emailRegex) && newValue.text.length <= maxPasswordLength) {
//                        password = newValue
//                    }
                    if ( newValue.text.contains("\n") && newValue.text != "" ) {
                        password =  TextFieldValue(newValue.text.replace("\n", ""))
                    } else {
                        if(newValue.text.length <= maxPasswordLength)
                            password = newValue
                    }
                                },
                label = { Text("Password") },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Icon(
                            painter = painterResource(if (passwordVisibility.value) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                            contentDescription = if (passwordVisibility.value) "Show Password" else "Hide Password"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(29, 35, 102, 500)
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate("ForgotPasswordView")
                    }
                )
            }

            Button(
                onClick = {
                    if (email.text.isEmpty() || password.text.isEmpty()) {
                        errorState.value = true
                    } else {
                        println("aea");
                        users.forEach{
                            println("entre")
                            if(email.text == it.email && password.text==it.password && it.type.lowercase()=="patient"){
                                navController.navigate("HomePatient/${it.id}")
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Login")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f), color = Color.Black)
                Text(
                    text = "OR",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Divider(modifier = Modifier.weight(1f), color = Color.Black)
            }

            Spacer(modifier = Modifier.padding(top = 15.dp))

            Button(
                onClick = { /* Acción al hacer clic en "Login with Google" */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Login with Google")
            }

            val text = AnnotatedString("New to PhysioCare? ") + buildAnnotatedString {
                pushStringAnnotation("URL", "new_in_therapy")
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append(" Register")
                }
                pop()
            }

            ClickableText(
                text = text,
                onClick = {  navController.navigate("SignUPView") },
                modifier = Modifier.padding(bottom = 8.dp)
            )

        }
    }
}