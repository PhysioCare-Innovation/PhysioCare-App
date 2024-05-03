package pe.edu.upc.digitalholics.appmobile.data.model

data class Appointment(
    val id: Int,
    val scheduledDate: String,
    val topic: String,
    val diagnosis: String,
    val done: String,
    val patient: Patient,
    val physiotherapist: Physiotherapist
)
