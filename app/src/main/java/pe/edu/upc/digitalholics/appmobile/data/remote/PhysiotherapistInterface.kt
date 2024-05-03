package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhysiotherapistInterface {
    @GET("physiotherapists")
    fun getAllPhysiotherapist(): Call<PhysiotherapistResponse>

    @GET("physiotherapists/{index}")
    fun getPhysiotherapistById(@Path("index")index: Int): Call<Physiotherapist>
}