package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInterface {

    @GET("users")
    fun getAllUsers(): Call<UserResponse>

    @GET("users/{index}")
    fun getUserById(@Path("index")index: String): Call<User>

    @POST("users")
    suspend fun postNewUser(@Body user: User): Response<User>

}