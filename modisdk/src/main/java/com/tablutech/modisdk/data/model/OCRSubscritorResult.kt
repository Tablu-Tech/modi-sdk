package com.tablutech.modisdk.data.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OCRSubscritorResult(var entries: Map<String, String>) {
    var name: String
    var firstName: String
    var middleName: String
    var lastName: String
    var phoneNumber: String
    var documentNumber: String
    var email: String
    var userId: String
    var nutel: String
    var nuit: String
    var nuib: String
    var serialNumber: String
    var height: String
    var birthDate: String
    var fatherName: String
    var motherName: String
    var hasSelfie: String
    var maritalStatus: String
    var country: String
    var city: String
    var district: String
    var houseNumber: String
    var physicalAddress: String
    var issuanceDate: String
    var expireDate: String
    var nationality: String
    var face: String
    var frontSide: String
    var backSide: String
    var portrait: String
    var selfie: String
    var residenceDistrictId: String
    var residenceProvinceId: String
    var bornProvinceId: String
    var gender: Int
    var bornCountryId: String

    init {
        val names = entries["Surname and given names"]?.split(" ") ?: listOf("")
        name = entries["Surname and given names"] ?: ""
        firstName = names.getOrElse(0) { "" }
        middleName = if (names.size > 2) names.subList(1, names.size - 1).joinToString(" ") else ""
        lastName = names.lastOrNull() ?: ""
        phoneNumber = entries["Phone number"] ?: ""
        documentNumber = entries["Personal number"] ?: ""
        email = entries["Email"] ?: ""
        userId = entries["User ID"] ?: ""
        nutel = entries["Nutel"] ?: ""
        nuit = entries["Nuit"] ?: ""
        nuib = entries["Nuib"] ?: ""
        serialNumber = entries["Serial number"] ?: ""
        height = entries["Height"] ?: ""
        birthDate = entries["Date of birth"]?.replace("/", "-") ?: ""
        fatherName = entries["Father's name"] ?: ""
        motherName = entries["Mother's name"] ?: ""
        hasSelfie = ""
        maritalStatus = entries["Marital status"] ?: ""
        country = entries["Issuing state"] ?: ""
        city = entries["Place of birth"] ?: ""
        district = ""
        houseNumber = ""
        physicalAddress = entries["Address"] ?: ""
        issuanceDate = entries["Date of issue"]?.replace("/", "-") ?: ""
        expireDate = entries["Date of expiry"]?.replace("/", "-") ?: ""
        nationality = entries["Issuing state code"] ?: ""
        face = entries["Face"] ?: ""
        frontSide = entries["Front side"] ?: ""
        backSide = entries["Back side"] ?: ""
        portrait = entries["Portrait"] ?: ""
        selfie = entries["Selfie"] ?: ""
        residenceDistrictId = entries["Address"]?.split(" ")?.last() ?: ""
        residenceProvinceId = entries["Address"]?.split(" ")?.last() ?: ""
        bornProvinceId = entries["PLACE OF BIRTH"] ?: ""
        gender = if (entries["Gender"] == "M") 1 else 2
        bornCountryId = entries["Place Of Birth"] ?: ""
    }
}


val translationMap = mapOf(

    "Date of birth" to "Data de nascimento",
    "Place of birth" to "Local de nascimento",
    "Personal number" to "Número do documento",
    "Sex" to "Sexo",
    "Address" to "Endereço",
    "Surname and given names" to "Sobrenome e nomes",
    "Issuing state" to "Estado emissor",
    "Age" to "Idade",
    "Date of expiry" to "Data de validade",
    "Date of issue" to "Data de emissão",
    "Surname" to "Sobrenome",
    "Given name" to "Nome",
    "Mother's name" to "Nome da mãe",
    "Nationality" to "Nacionalidade",
    "Nationality code" to "Código de nacionalidade",
    "Optional data" to "Dados opcionais",
    "Place of issue" to "Local de emissão",
    "Father's name" to "Nome do pai",
    "Marital status" to "Estado civil",
)


fun translateKey(key: String): String {
    return translationMap[key] ?: key
}

val fieldsToRemove = listOf(
    "MRZ lines",
    "Check digit for document number" ,
    "Check digit for date of birth" ,
    "Check digit for date of expiry" ,
    "Final check digit",
    "Months to expire",
    "Years since issue",
    "MRZ Type",
    "Issuing state code" ,
    "Height",
    "Document number",
)

fun joinPersonalNames(names : String? = "" , surname : String ? = "") : String {
    return names +" "+surname
}

fun formatBornDate(dateString: String?): String {
    if (dateString.isNullOrEmpty()) {
        return "Data inválida"
    }

    val originalFormat = SimpleDateFormat("M/d/yy", Locale.ENGLISH)
    val targetFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

    return try {
        val date: Date = originalFormat.parse(dateString) ?: return "Data inválida"
        targetFormat.format(date)
    } catch (e: ParseException) {
        "Formato de data inválido"
    }
}
