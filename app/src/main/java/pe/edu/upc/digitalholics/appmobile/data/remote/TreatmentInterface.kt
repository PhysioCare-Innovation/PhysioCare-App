package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TreatmentInterface {
    @GET("treatments")
    fun getAllTreatments(): Call<TreatmentResponse>

    @GET("treatments/{index}")
    fun getTreatmentById(@Path("index")index: String): Call<Treatment>


//    @GET("treatments_by_patient/{patientId}")
//    fun getTreatmentByPatientId(@Path("patientId")patientId: String): Call<TreatmentResponse>

}