package pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pe.edu.upc.digitalholics.appmobile.R
import pe.edu.upc.digitalholics.appmobile.data.model.Appointment
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.User
import pe.edu.upc.digitalholics.appmobile.data.remote.ApiClient
import pe.edu.upc.digitalholics.appmobile.data.remote.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    var rol by remember {
        mutableStateOf("")
    }

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.backgrund),
                contentDescription = "Image",
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Sign up",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, end = 5.dp)
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            val email = remember { mutableStateOf("") }
            val fullName = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val passwordVisibility = remember { mutableStateOf(false) }
            val selectedRole = remember { mutableStateOf("Paciente") }
            val termsAccepted = remember { mutableStateOf(false) }
            val users = remember { mutableStateOf(emptyList<User>()) }

            val errorMessage = remember { mutableStateOf("") }
            val createMessage = remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()


            val userInterface = ApiClient.buildUserInterface()
            val patientInterface = ApiClient.buildPatientInterface()
            val physiotherapistInterface = ApiClient.buildPhysiotherapistInterface()

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fullName.value,
                onValueChange = { fullName.value = it },
                label = { Text("FullName") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
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

            //

            rol = Demo_ExposedDropdownMenuBox()

            //

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = termsAccepted.value,
                    onCheckedChange = { termsAccepted.value = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    textAlign = TextAlign.Start,
                    text = "I accept the terms and conditions",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            val getAllUser = userInterface.getAllUsers()

            LaunchedEffect(Unit) {

                getAllUser.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        if (response.isSuccessful) {
                            users.value = response.body()?.users!!
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }

            Button(
                onClick = {
                    val newUser = User(
                        id = 0,
                        email = email.value,
                        password = password.value,
                        type = rol,
                    )
                    val newPatient = Patient(
                        id = 0,
                        userId = users.value[users.value.size-1].id+1,
                        firstName = fullName.value.split(' ')[0],
                        lastName = fullName.value.split(' ')[1],
                        appointmentQuantity = 0,
                        email = email.value,
                        age = 30,
                        photoUrl = "https://i.ytimg.com/vi/8KUR1Lq_5A8/hqdefault_live.jpg",
                        birthdayDate = "2023-02-14",
                    )
                    if(rol=="patient"){
                        coroutineScope.launch {
                            try {
                                val response = userInterface.postNewUser(newUser)
                                val response2 = patientInterface.postNewPatients(newPatient)
                                if (response.isSuccessful && response2.isSuccessful) {
                                    createMessage.value = "Se guardó el tratamiento"
                                    navController.navigate("LoginView")
                                } else {
                                    errorMessage.value =
                                        "Error en la llamada POST. Código de respuesta: ${response.code()}"
                                        "Error en la llamada POST. Código de respuesta: ${response2.code()}"
                                    val errorBody = response.errorBody()?.string()
                                    val errorBody2 = response2.errorBody()?.string()
                                    errorBody?.let { errorMessage.value += newUser }
                                    errorBody2?.let { errorMessage.value += newPatient }
                                }
                            } catch (e: Exception) {
                                errorMessage.value = "Excepción durante la llamada POST: ${e.message}"
                            }
                        }
                    }else{
                        showDialog=true
                    }

                },
                enabled = termsAccepted.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    if (termsAccepted.value) MaterialTheme.colorScheme.primary else Color.Gray
                )
            ) {
                Text(text = "Done")
                Text(text = errorMessage.value)
            }

            val text = AnnotatedString("Join us before? ") + buildAnnotatedString {
                pushStringAnnotation("URL", "join_befoew_therapy")
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                    append(" Login")
                }
                pop()
            }

            ClickableText(
                text = text,
                onClick = {  navController.navigate("LoginView") },
                modifier = Modifier.padding(bottom = 8.dp)
            )

        }
    }

    if (showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.comingsoon),
                    contentDescription = "Stars",
                    tint = Color(0,160,0),
                    modifier = Modifier
                        .size(140.dp)
                    //.absolutePadding(bottom = 5.dp)
                )
            },
            title = {
                Text(text = "Comprate Ipone brother")
            },

            confirmButton = {
                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0,122,240)
                ), modifier = Modifier.absolutePadding(right = 60.dp)
                    ,onClick = { showDialog = false
                        navController.navigate("LoginView")}) {
                    Text(text = "Go Pipipi")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }

}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(): String {
    val context = LocalContext.current
    val roles = arrayOf("Rol","patient","physiotherapist",)
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(roles[0]) }
    var rolLabel by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                label = { Text(rolLabel) },
                modifier = Modifier
                    .menuAnchor()
                    .absolutePadding(top = 10.dp)
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                roles.forEach { item ->
                    if (item!="Rol")
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            rolLabel = "Rol"
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
    }

    return selectedText.lowercase()
}




