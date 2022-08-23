package yunho.app.autocrypt.Data.Response

import yunho.app.autocrypt.Data.Entity.CenterEntity

data class CenterResponse(
    val id: Int,
    val centerName: String,
    val sido: String,
    val sigungu: String,
    val facilityName: String,
    val zipCode: String,
    val address: String,
    val lat: String,
    val lng: String,
    val createdAt: String,
    val updatedAt: String,
    val centerType: String,
    val org: String,
    val phoneNumber: String
) {
    fun toCenterEntity(): CenterEntity =
        CenterEntity(
            id,
            centerName,
            sido,
            sigungu,
            facilityName,
            zipCode,
            address,
            lat,
            lng,
            createdAt,
            updatedAt,
            centerType,
            org,
            phoneNumber
        )
}