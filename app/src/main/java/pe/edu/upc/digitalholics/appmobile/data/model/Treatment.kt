package pe.edu.upc.digitalholics.appmobile.data.model

data class Treatment(
    val id: String,
    val title: String,
    val description: String,
    val photoUrl: String,
    val sessionsQuantity: String,
    val physiotherapist: Physiotherapist,

)
