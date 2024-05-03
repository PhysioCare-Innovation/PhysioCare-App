package pe.edu.upc.digitalholics.appmobile.ui.screens.Navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.digitalholics.appmobile.data.model.Appointment
import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import pe.edu.upc.digitalholics.appmobile.data.model.Review
import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import pe.edu.upc.digitalholics.appmobile.data.model.TreatmentByPatient
import pe.edu.upc.digitalholics.appmobile.data.model.User
import pe.edu.upc.digitalholics.appmobile.data.remote.ApiClient
import pe.edu.upc.digitalholics.appmobile.data.remote.AppointmentResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.PatientResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.PhysiotherapistResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.ReviewResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.TreatmentByPatientResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.TreatmentResponse
import pe.edu.upc.digitalholics.appmobile.data.remote.UserResponse
import pe.edu.upc.digitalholics.appmobile.ui.screens.AddRiew.AddReview
import pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews.EnterCodeScream
import pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews.ForgotPasswordScream
import pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews.LoginScreen
import pe.edu.upc.digitalholics.appmobile.ui.screens.InitialsViews.SignUpScreen
import pe.edu.upc.digitalholics.appmobile.ui.screens.PatientProfile.PatientProfile
//import pe.edu.upc.digitalholics.appmobile.ui.srceens.PatientsDetails.Patient
import pe.edu.upc.digitalholics.appmobile.ui.screens.PatientsDetails.PatientDetails
import pe.edu.upc.digitalholics.appmobile.ui.screens.PhysiotherapistProfile.PhysiotherapistProfile
import pe.edu.upc.digitalholics.appmobile.ui.screens.PhysiotherapistsList.PhysiotherapistList
import pe.edu.upc.digitalholics.appmobile.ui.screens.TreatmentDetails.TreatmentDetails
import pe.edu.upc.digitalholics.appmobile.ui.screens.TreatmentList.Treatments
import pe.edu.upc.digitalholics.appmobile.ui.srceens.AppointmentList.AppointmentList
import pe.edu.upc.digitalholics.appmobile.ui.srceens.HomePatient.Home
import pe.edu.upc.digitalholics.appmobile.ui.srceens.Payment.Payment
import pe.edu.upc.digitalholics.appmobile.ui.srceens.Schedule.Schedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("SuspiciousIndentation")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val randomNumber = remember { mutableStateOf(0) }

    NavHost(navController = navController, startDestination = "LoginView") {



        composable("Payment/{index}/{topic}/{date}",
            arguments = listOf(navArgument("index") { type = NavType.StringType },
                navArgument("topic") {type = NavType.StringType},
                navArgument("date") {type = NavType.StringType},
            )
        ) {

            val index = it.arguments?.getString("index") as String
            val topic = it.arguments?.getString("topic") as String

            val date = it.arguments?.getString("date") as String

            val physiotherapist = remember {
                mutableStateOf(
                    Physiotherapist(
                        id = 0,
                        firstName = "",
                        paternalSurname = "",
                        maternalSurname = "",
                        age = 0,
                        rating = 0,
                        location = "",
                        photoUrl = "",
                        birthdayDate = "",
                        consultationsQuantity = 0,
                        specialization = "",
                        email = "",
                        userId = 0
                    )
                )
            }

            val driverInterface = ApiClient.buildPhysiotherapistInterface()
            val getDriver = driverInterface.getPhysiotherapistById(index.toInt())

            getDriver.enqueue(object : Callback<Physiotherapist> {
                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>) {
                    if (response.isSuccessful) {
                        physiotherapist.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {
                }
            })


            Payment(physiotherapist = physiotherapist.value,
                topic = topic, date = date,
                navController = navController)
        }

        composable("Schedule/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })) {

            val index = it.arguments?.getString("index") as String

            val physiotherapist = remember {
                mutableStateOf(
                    Physiotherapist(
                        id = 0,
                        firstName = "",
                        paternalSurname = "",
                        maternalSurname = "",
                        age = 0,
                        rating = 0,
                        location = "",
                        photoUrl = "",
                        birthdayDate = "",
                        consultationsQuantity = 0,
                        specialization = "",
                        email = "",
                        userId = 0
                    )
                )
            }

            val driverInterface = ApiClient.buildPhysiotherapistInterface()
            val getDriver = driverInterface.getPhysiotherapistById(index.toInt())

            getDriver.enqueue(object : Callback<Physiotherapist> {
                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>) {
                    if (response.isSuccessful) {
                        physiotherapist.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {
                }
            })


            Schedule(
                physiotherapist = physiotherapist.value,
                navController = navController
            )

        }
        composable(
            "patient/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })
        ) {
            val index = it.arguments?.getString("index") as String

            val patients = remember {
                mutableStateOf(
                    Patient(
                        0,
                        0,
                        " ",
                        "20",
                        0,
                        "jose@gmail.com",
                        0,
                        "https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg",
                        ""
                    )
                )
            }

            val driverInterface = ApiClient.buildPatientInterface()
            val getDriver = driverInterface.getPatientById(index)

            getDriver.enqueue(object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                    if (response.isSuccessful) {
                        patients.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Patient>, t: Throwable) {
                }
            })

            PatientDetails(patient = patients.value)
        }


        composable("AppointmentList") {

            val appointments = remember {
                //mutableStateOf(Appointment("1","Jose","Del Carpio","20","30","jose@gmail.com","2"))
                mutableStateOf(emptyList<Appointment>())
            }

            AppointmentList(
                //patients = patients.value,
                appointments = appointments.value,
                navController = navController
            )

            //AppointmentList(0, appointments = appointments.value)

            val appointmentInterface = ApiClient.buildAppointmentInterface()
            val getAllAppointment = appointmentInterface.getAllAppointments()

            getAllAppointment.enqueue(object : Callback<AppointmentResponse> {
                override fun onResponse(
                    call: Call<AppointmentResponse>,
                    response: Response<AppointmentResponse>
                ) {
                    if (response.isSuccessful) {
                        appointments.value = response.body()?.appointments!!

                    }
                }

                override fun onFailure(call: Call<AppointmentResponse>, t: Throwable) {
                }
            })
            //appointments.value. +=
        }

        //treatmentList
        composable("TreatmentList") {
            val treatments = remember {
                mutableStateOf(emptyList<Treatment>())
            }

            //PatientDetails(patient = patients.value)

            val treatmentInterface = ApiClient.buildTreatmentInterface()
            val getAllTreatments = treatmentInterface.getAllTreatments()

            getAllTreatments.enqueue(object : Callback<TreatmentResponse> {
                override fun onResponse(
                    call: Call<TreatmentResponse>,
                    response: Response<TreatmentResponse>
                ) {
                    if (response.isSuccessful) {
                        treatments.value = response.body()?.treatments!!

                    }
                }

                override fun onFailure(call: Call<TreatmentResponse>, t: Throwable) {

                }
            })
            Treatments(
                treatments = treatments.value,
                selectTreatment = { index ->
                    navController.navigate("treatment/$index")
                },navController
            )
        }

        //treatmentDetail
        composable(
            "treatment/{index}",
            arguments = listOf(navArgument("index") { type = NavType.StringType })
        ) {
            val index = it.arguments?.getString("index") as String
            val physiotherapist = Physiotherapist(1, "Roberto","Loza","Perez",45,4,"Lima",
                "","04/05/1994",20,"Neck","roberto@email.com",2)


            val treatment = remember {
                mutableStateOf(
                    Treatment(
                        " "," "," "," ","",physiotherapist
                    )
                )
            }

            val treatmentInterface = ApiClient.buildTreatmentInterface()
            val getTreatment = treatmentInterface.getTreatmentById(index)

            getTreatment.enqueue(object : Callback<Treatment> {
                override fun onResponse(call: Call<Treatment>, response: Response<Treatment>) {
                    if (response.isSuccessful) {
                        treatment.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Treatment>, t: Throwable) {
                }
            })



            val treatments = remember {
                //mutableStateOf(Patient("1","Jose","Del Carpio","20","30","jose@gmail.com","2","https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg"))
                mutableStateOf(emptyList<TreatmentByPatient>())
            }


            val treatmentInterface2 = ApiClient.buildTreatmentByPatientInterface()
            val getTreatmentByPatientId = treatmentInterface2.getTreatmentsByPatient()
            val context = LocalContext.current
            val sharedPreferences = remember {
                context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
            }
            val id = sharedPreferences.getString("userLogged", "0")
            getTreatmentByPatientId.enqueue(object : Callback<TreatmentByPatientResponse> {
                override fun onResponse(
                    call: Call<TreatmentByPatientResponse>,
                    response: Response<TreatmentByPatientResponse>
                ) {
                    if (response.isSuccessful) {
                        treatments.value = response.body()?.treatmentsByPatient!!

                    }
//                    else{
//                        mutableStateOf(Treatment("1", "title", "description", "https://www.metropolsalud.com/wp-content/uploads/2021/07/diatermia.jpg", "4", Physiotherapist(1, "Nombre", "", "", 40, 45, "", "", "", 30, "", "", 5))
//                    }
                }
                //                patient.value = response.body()!!


                override fun onFailure(call: Call<TreatmentByPatientResponse>, t: Throwable) {

                }
            })


            treatments.value = treatments.value.filter { it.patient.id == id!!.toInt() }
            var enrolled = false
                treatments.value.forEach {
                if(it.treatment.id ==  treatment.value.id){
                    enrolled = true
                }
            }

            TreatmentDetails(treatment = treatment.value,navController, enrolled)
        }

        composable("patient"){
            //val index = it.arguments?.getString("index") as String

            val context = LocalContext.current
            val sharedPreferences = remember {
                context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
            }
            val id = sharedPreferences.getString("userLogged", "0")

            val patient = remember {
                mutableStateOf(
                    Patient(0,0,"","",0,"",0,"", " "
                    )
                )
            }



            val patientInterface = ApiClient.buildPatientInterface()
            val getPatient = patientInterface.getPatientById(id.toString())

            getPatient.enqueue(object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                    if (response.isSuccessful) {
                        patient.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Patient>, t: Throwable) {
                }
            })

            PatientProfile(patient = patient.value,navController)

        }


        composable("physiotherapistList") {
            val physiotherapists = remember {
                mutableStateOf(emptyList<Physiotherapist>())
            }

            //PatientDetails(patient = patients.value)

            val physiotherapistInterface = ApiClient.buildPhysiotherapistInterface()
            val getAllPhysiotherapist = physiotherapistInterface.getAllPhysiotherapist()

            getAllPhysiotherapist.enqueue(object : Callback<PhysiotherapistResponse> {
                override fun onResponse(
                    call: Call<PhysiotherapistResponse>,
                    response: Response<PhysiotherapistResponse>
                ) {
                    if (response.isSuccessful) {
                        physiotherapists.value = response.body()?.physiotherapists!!

                    }
                }

                override fun onFailure(call: Call<PhysiotherapistResponse>, t: Throwable) {

                }
            })

            PhysiotherapistList(navController,
                physiotherapists = physiotherapists.value
            )
        }

        composable("physiotherapistProfile/{index}",  arguments = listOf(navArgument("index") { type = NavType.StringType })){
            val index = it.arguments?.getString("index") as String

            val physiotherapist = remember {
                mutableStateOf(
                    Physiotherapist(
                        0,
                        " ",
                        " ",
                        " ",
                        0,
                        0,
                        " ",
                        "",
                        "",
                        0,
                        " ",
                        " ",
                        0
                    )
                )
            }

            val reviews = remember{
                mutableStateOf(emptyList<Review>())
            }

            val physiotherapistInterface = ApiClient.buildPhysiotherapistInterface()
            val getPhysiotherapist = physiotherapistInterface.getPhysiotherapistById(index.toInt())

            val reviewInterface = ApiClient.buildReviewInterface()
            val getReviews = reviewInterface.getAllReviews()

            getPhysiotherapist.enqueue(object : Callback<Physiotherapist> {
                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>) {
                    if (response.isSuccessful) {
                        physiotherapist.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {
                }
            })

            getReviews.enqueue(object : Callback<ReviewResponse> {
                override fun onResponse(
                    call: Call<ReviewResponse>,
                    response: Response<ReviewResponse>
                ) {
                    if (response.isSuccessful) {
                        reviews.value = response.body()?.reviews!!

                    }
                }

                override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {

                }
            })

            PhysiotherapistProfile(physiotherapist.value,reviews.value,navController)

        }

        composable("addReview/{physiotherapistId}", arguments = listOf(navArgument("physiotherapistId") { type = NavType.StringType })){
            val physiotherapistId = it.arguments?.getString("physiotherapistId") as String
            val physiotherapist = remember {
                mutableStateOf(
                    Physiotherapist(
                        0,
                        " ",
                        " ",
                        " ",
                        0,
                        0,
                        " ",
                        "",
                        "",
                        0,
                        " ",
                        " ",
                        0
                    )
                )
            }

            val reviews = remember{
                mutableStateOf(emptyList<Review>())
            }

            val physiotherapistInterface = ApiClient.buildPhysiotherapistInterface()
            val getPhysiotherapist = physiotherapistInterface.getPhysiotherapistById(physiotherapistId.toInt())

            val reviewInterface = ApiClient.buildReviewInterface()
            val getReviews = reviewInterface.getAllReviews()

            getPhysiotherapist.enqueue(object : Callback<Physiotherapist> {
                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>) {
                    if (response.isSuccessful) {
                        physiotherapist.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {
                }
            })

            getReviews.enqueue(object : Callback<ReviewResponse> {
                override fun onResponse(
                    call: Call<ReviewResponse>,
                    response: Response<ReviewResponse>
                ) {
                    if (response.isSuccessful) {
                        reviews.value = response.body()?.reviews!!

                    }
                }

                override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {

                }
            })
            AddReview(physiotherapist = physiotherapist.value, reviews =reviews.value , navController =navController )

        }

        composable("HomePatient/{patientId}",arguments = listOf(navArgument("patientId") { type = NavType.StringType })){
            val patientId = it.arguments?.getString("patientId") as String
            val context = LocalContext.current
            val sharedPreferences = remember {
                context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
            }
            val editor = sharedPreferences.edit()


//            val patients = remember {
//                mutableStateOf(emptyList<Patient>())
//            }
            val treatments = remember {
                //mutableStateOf(Patient("1","Jose","Del Carpio","20","30","jose@gmail.com","2","https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg"))
                mutableStateOf(emptyList<TreatmentByPatient>())
            }
            // val patientInterface = ApiClient.buildPatientInterface()


//            val index = it.arguments?.getString("index") as String

//            val treatments2 = remember {
//                mutableStateOf(Patient(1,1,"Del Carpio","20",30,"jose@gmail.com",2,"https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg"))
////                mutableStateOf(emptyList<TreatmentByPatient>())
//            }


            val patients = remember {
                mutableStateOf(
                    Patient(
                        1,
                        1,
                        "",
                        "Del Carpio",
                        20,
                        "jose@gmail.com",
                        30,
                        "https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg",
                        "2023-07-25"
                    )
                )
            }


            val patients2 = remember {
                //mutableStateOf(Patient("1","Jose","Del Carpio","20","30","jose@gmail.com","2","https://img.europapress.es/fotoweb/fotonoticia_20081004164743_420.jpg"))
                mutableStateOf(emptyList<Patient>())
            }
            val driverInterface = ApiClient.buildPatientInterface()


            val getDriver = driverInterface.getPatientById(patientId)
            val getAllPatient = driverInterface.getAllPatients()

            getDriver.enqueue(object : Callback<Patient> {
                override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                    if (response.isSuccessful) {
                        patients.value = response.body()!!
                    }
                }

                override fun onFailure(call: Call<Patient>, t: Throwable) {
                }
            })


            getAllPatient.enqueue(object : Callback<PatientResponse> {
                override fun onResponse(
                    call: Call<PatientResponse>,
                    response: Response<PatientResponse>
                ) {
                    if (response.isSuccessful) {
                        patients2.value = response.body()?.patients!!
                    }
                }

                override fun onFailure(call: Call<PatientResponse>, t: Throwable) {
                }
            })

            patients2.value.forEach{
                println("entre")
                if(it.userId.toString() == patientId){
                    patients.value = it
                }
            }
            editor.putString("userLogged", patients.value.id.toString())
            editor.apply()



            val treatmentInterface = ApiClient.buildTreatmentByPatientInterface()
            val getTreatmentByPatientId = treatmentInterface.getTreatmentsByPatient()

            getTreatmentByPatientId.enqueue(object : Callback<TreatmentByPatientResponse> {
                override fun onResponse(
                    call: Call<TreatmentByPatientResponse>,
                    response: Response<TreatmentByPatientResponse>
                ) {
                    if (response.isSuccessful) {
                        treatments.value = response.body()?.treatmentsByPatient!!

                    }
//                    else{
//                        mutableStateOf(Treatment("1", "title", "description", "https://www.metropolsalud.com/wp-content/uploads/2021/07/diatermia.jpg", "4", Physiotherapist(1, "Nombre", "", "", 40, 45, "", "", "", 30, "", "", 5))
//                    }
                }
                //                patient.value = response.body()!!


                override fun onFailure(call: Call<TreatmentByPatientResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })






//            HomePatient(
//               treatment = treatments.value
//            )

            val physiotherapists = remember {
                mutableStateOf(emptyList<Physiotherapist>())
            }

            //PatientDetails(patient = patients.value)

            val physiotherapist = remember {
                mutableStateOf(
                    Physiotherapist(
                        id = 0,
                        firstName = "",
                        paternalSurname = "",
                        maternalSurname = "",
                        age = 0,
                        rating = 0,
                        location = "",
                        photoUrl = "",
                        birthdayDate = "",
                        consultationsQuantity = 0,
                        specialization = "",
                        email = "",
                        userId = 0
                    )
                )
            }

            val physiotherapistInterface = ApiClient.buildPhysiotherapistInterface()
            val getPhysiotherapistById = physiotherapistInterface.getPhysiotherapistById(1)

            getPhysiotherapistById.enqueue(object : Callback<Physiotherapist> {
                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>){
                    if (response.isSuccessful) {
                        physiotherapist.value = response.body()!!

                    }
                }

                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {

                }
            })

//            getDriver.enqueue(object : Callback<Physiotherapist> {
//                override fun onResponse(call: Call<Physiotherapist>, response: Response<Physiotherapist>) {
//                    if (response.isSuccessful) {
//                        physiotherapist.value = response.body()!!
//                    }
//                }
//
//                override fun onFailure(call: Call<Physiotherapist>, t: Throwable) {
//                }
//            })

//            getDriver.enqueue(object : Callback<Patient> {
//                override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
//                    if (response.isSuccessful) {
//                        patients.value = response.body()!!
//                    }
//                }
//
//                override fun onFailure(call: Call<Patient>, t: Throwable) {
//                }
//            })

            Home(
                treatments = treatments.value.filter { it.patient.id == patients.value.id },
                selectTreatment = { index ->
                    navController.navigate("treatment/$index")
                },
                patient = patients.value,
                physiotherapist = physiotherapist.value,
                navController = navController
            )

        }

        composable("SignUPView"){
            SignUpScreen(navController)
        }

        composable("ForgotPasswordView"){

            val users = remember {
                mutableStateOf(emptyList<User>())
            }

            val userInterface = ApiClient.buildUserInterface()
            val getAllUser = userInterface.getAllUsers()

            getAllUser.enqueue(object : Callback<UserResponse>{
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

            ForgotPasswordScream(users.value,navController)
        }

        composable("LoginView"){

            val users = remember {
                mutableStateOf(emptyList<User>())
            }

            val userInterface = ApiClient.buildUserInterface()
            val getAllUser = userInterface.getAllUsers()

            getAllUser.enqueue(object : Callback<UserResponse>{
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

            LoginScreen(users.value,navController)
        }

        composable("EnterCodeView/{userId}/{randomNumber}", arguments = listOf(navArgument("userId") { type = NavType.StringType }, navArgument("randomNumber") { type = NavType.IntType })) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val randomNumber = backStackEntry.arguments?.getInt("randomNumber") ?: 0


            val users = remember {
                mutableStateOf(emptyList<User>())
            }

            val userInterface = ApiClient.buildUserInterface()
            val getAllUser = userInterface.getAllUsers()

            getAllUser.enqueue(object : Callback<UserResponse>{
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

            EnterCodeScream(users.value,userId = userId, randomNumber = randomNumber, navController)
        }
    }
}


