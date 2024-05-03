package pe.edu.upc.digitalholics.appmobile.data.remote

import pe.edu.upc.digitalholics.appmobile.data.model.Review
import pe.edu.upc.digitalholics.appmobile.data.model.Treatment
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewInterface {
    @GET("reviews")
    fun getAllReviews(): Call<ReviewResponse>

    @GET("reviews/{index}")
    fun getReviewById(@Path("index")index: String): Call<Review>
    @POST("reviews")
    suspend fun postNewReview(@Body review: Review): Response<Review>

}