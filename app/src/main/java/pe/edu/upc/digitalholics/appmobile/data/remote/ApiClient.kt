package pe.edu.upc.digitalholics.appmobile.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://backendphysiocare.up.railway.app/api/v1/"
    private var patientInterface: PatientInterface? = null
    private var treatmentInterface: TreatmentInterface? = null
    private var reviewInterface: ReviewInterface? = null
    private var physiotherapistInterface: PhysiotherapistInterface? = null
    private var appointmentInterface: AppointmentInterface? = null
    private var userInterface: UserInterface? = null
    private var treatmentByPatient: TreatmentByPatient? = null

    //esto llama al API
    fun buildPatientInterface(): PatientInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        patientInterface = retrofit.create(PatientInterface::class.java)
        return patientInterface as PatientInterface
    }

    fun buildTreatmentInterface(): TreatmentInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        treatmentInterface = retrofit.create(TreatmentInterface::class.java)
        return treatmentInterface as TreatmentInterface
    }

    fun buildReviewInterface(): ReviewInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        reviewInterface = retrofit.create(ReviewInterface::class.java)
        return reviewInterface as ReviewInterface
    }

    fun buildPhysiotherapistInterface(): PhysiotherapistInterface{
        val retrofit=Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        physiotherapistInterface = retrofit.create(PhysiotherapistInterface::class.java)
        return physiotherapistInterface as PhysiotherapistInterface
    }

    fun buildAppointmentInterface(): AppointmentInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        appointmentInterface = retrofit.create(AppointmentInterface::class.java)
        return appointmentInterface as AppointmentInterface
    }

    fun buildUserInterface(): UserInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        userInterface = retrofit.create(UserInterface::class.java)
        return  userInterface as UserInterface
    }

    fun buildTreatmentByPatientInterface(): TreatmentByPatient{
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        treatmentByPatient = retrofit.create(TreatmentByPatient::class.java)
        return  treatmentByPatient as TreatmentByPatient
    }


}