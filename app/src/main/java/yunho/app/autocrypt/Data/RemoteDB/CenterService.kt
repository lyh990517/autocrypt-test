package yunho.app.autocrypt.Data.RemoteDB

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import yunho.app.autocrypt.Data.Entity.CenterEntity
import yunho.app.autocrypt.Data.Response.CentersResponse

interface CenterService {

    @GET("api/15077586/v1/centers")
    suspend fun getCenterList(
        @Query("page") page : Int,
        @Query("perPage") perPage : Int,
        @Query("serviceKey") serviceKey : String
    ): Response<CentersResponse>
}