package yunho.app.autocrypt.Data.Repository

import yunho.app.autocrypt.Data.Entity.CenterEntity

interface Repository {
    suspend fun getCenterList(
        page: Int,
        perPage: Int,
        serviceKey: String
    ): List<CenterEntity>

    suspend fun saveCenterData(centerEntity: CenterEntity)

    suspend fun getCenterListFromLocalDB(): List<CenterEntity>

    suspend fun getSpecificCenterInfo(id: Long): CenterEntity
}