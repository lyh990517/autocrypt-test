package yunho.app.autocrypt.Data.Repository

import yunho.app.autocrypt.Data.Entity.CenterEntity

interface Repository {
    suspend fun getCenterList(
        page: Int,
        perPage: Int,
        serviceKey : String
    ) : List<CenterEntity>
}