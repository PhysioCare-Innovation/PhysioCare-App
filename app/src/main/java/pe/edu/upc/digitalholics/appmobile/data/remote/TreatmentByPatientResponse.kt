package pe.edu.upc.digitalholics.appmobile.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import pe.edu.upc.digitalholics.appmobile.data.model.TreatmentByPatient

data class TreatmentByPatientResponse(
    @SerializedName("content")
    val treatmentsByPatient : ArrayList<TreatmentByPatient>
)