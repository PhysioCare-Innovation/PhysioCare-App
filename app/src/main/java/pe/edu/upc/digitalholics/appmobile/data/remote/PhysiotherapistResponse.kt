package pe.edu.upc.digitalholics.appmobile.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.digitalholics.appmobile.data.model.Physiotherapist

data class PhysiotherapistResponse(
    @SerializedName("content")
    val physiotherapists : ArrayList<Physiotherapist>
)