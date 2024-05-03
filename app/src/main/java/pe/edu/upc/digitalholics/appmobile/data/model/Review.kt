package pe.edu.upc.digitalholics.appmobile.data.model

data class Review(
    val id: Int,
    val description: String,
    val stars: Int,
    val patient: Patient,
    val physiotherapist: Physiotherapist

)
