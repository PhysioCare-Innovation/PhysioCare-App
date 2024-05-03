package pe.edu.upc.digitalholics.appmobile.data.remote

import com.google.gson.annotations.SerializedName
import pe.edu.upc.digitalholics.appmobile.data.model.Review

data class ReviewResponse (
    @SerializedName("content")
    val reviews : ArrayList<Review>
        )