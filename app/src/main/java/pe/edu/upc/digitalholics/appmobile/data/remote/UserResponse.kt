package pe.edu.upc.digitalholics.appmobile.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.digitalholics.appmobile.data.model.User

data class UserResponse(
    @SerializedName("content")
    val users : ArrayList<User>
)
