package yunho.app.autocrypt.Data.Repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Data.LocalDB.Dao.CenterDao
import yunho.app.autocrypt.Data.RemoteDB.CenterService

class CenterRepository(
    private val CenterAPI : CenterService,
    private val IODispatcher : CoroutineDispatcher,
    private val centerDao : CenterDao
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

    override suspend fun saveCenterData(centerEntity: CenterEntity) = withContext(IODispatcher){
        centerDao.insertOne(centerEntity)
    }

    override suspend fun getCenterListFromLocalDB(): List<CenterEntity> = withContext(IODispatcher){
        centerDao.getAll()
    }

    override suspend fun getSpecificCenterInfo(id: Long): CenterEntity = withContext(IODispatcher) {
        centerDao.getOne(id)
    }

}