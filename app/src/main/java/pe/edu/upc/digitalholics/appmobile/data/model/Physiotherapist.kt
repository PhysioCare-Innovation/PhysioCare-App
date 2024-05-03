package pe.edu.upc.digitalholics.appmobile.data.model

data class Physiotherapist(
    val id: Int,
    val firstName: String,
    val paternalSurname: String,
    val maternalSurname: String,
    val age: Int,
    val rating: Int,
    val location: String,
    val photoUrl: String,
    val birthdayDate: String,
    val consultationsQuantity: Int,
    val specialization: String,
    val email: String,
    val userId: Int
)


