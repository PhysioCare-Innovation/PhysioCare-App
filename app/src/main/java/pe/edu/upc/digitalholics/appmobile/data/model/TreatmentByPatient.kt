package pe.edu.upc.digitalholics.appmobile.data.model

data class TreatmentByPatient (
    val id: String,
    val treatment: Treatment,
    val patient: Patient,
    val registrationDate: String,
    val progress: Double
)