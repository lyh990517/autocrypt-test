package yunho.app.autocrypt.Data.Repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Data.RemoteDB.CenterService

class CenterRepository(
    private val CenterAPI : CenterService,
    private val IODispatcher : CoroutineDispatcher
) : Repository{
    override suspend fun getCenterList(
        page: Int,
        perPage: Int,
        serviceKey: String
    ): List<CenterEntity> = withContext(IODispatcher){
        val response = CenterAPI.getCenterList(page,perPage, serviceKey)
        return@withContext if(response.isSuccessful){
            response.body()?.data?.map { it.toCenterEntity() } ?: listOf()
        }else{
            listOf()
        }
    }

}