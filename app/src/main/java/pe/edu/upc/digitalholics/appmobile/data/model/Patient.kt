package pe.edu.upc.digitalholics.appmobile.data.model

data class Patient(
    val id: Int,
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val appointmentQuantity: Int,
    val email: String,
    val age: Int,
    val photoUrl: String,
    val birthdayDate: String,
)
