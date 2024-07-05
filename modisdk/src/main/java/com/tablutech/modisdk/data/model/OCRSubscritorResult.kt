package com.tablutech.modisdk.data.model

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
