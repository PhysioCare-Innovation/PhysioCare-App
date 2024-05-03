package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import pe.edu.upc.digitalholics.appmobile.data.model.TreatmentByPatient
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TreatmentByPatient {
//    @GET("treatments_by_patient/{patientId}")
//    fun getTreatmentByPatientId(@Path("patientId")patientId: String): Call<TreatmentByPatientResponse>

    @GET("treatments_by_patient")
    fun getTreatmentsByPatient(): Call<TreatmentByPatientResponse>


    @POST("treatments_by_patient")
    suspend fun postNewTreatmentByPatient(@Body treatment: TreatmentByPatient): Response<TreatmentByPatient>
}