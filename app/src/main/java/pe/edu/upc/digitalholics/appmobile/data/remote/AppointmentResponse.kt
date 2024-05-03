package pe.edu.upc.digitalholics.appmobile.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.digitalholics.appmobile.data.model.Appointment
import pe.edu.upc.digitalholics.appmobile.data.model.Patient

data class AppointmentResponse(
    @SerializedName("content")
    val appointments : ArrayList<Appointment>
)