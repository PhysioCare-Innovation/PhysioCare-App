package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.Patient
import pe.edu.upc.digitalholics.appmobile.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface PatientInterface {

    @GET("patients")
    fun getAllPatients(): Call<PatientResponse>

    @GET("patients/{index}")
    fun getPatientById(@Path("index")index: String): Call<Patient>

    @POST("patients")
    suspend fun postNewPatients(@Body patient: Patient): Response<Patient>


}