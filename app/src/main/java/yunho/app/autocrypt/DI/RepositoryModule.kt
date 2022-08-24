package yunho.app.autocrypt.DI

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import yunho.app.autocrypt.Data.LocalDB.Dao.CenterDao
import yunho.app.autocrypt.Data.RemoteDB.CenterService
import yunho.app.autocrypt.Data.Repository.CenterRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        CenterDao : CenterDao,
        IODispatcher : CoroutineDispatcher,
        CenterAPI : CenterService
    ) : CenterRepository{
        return CenterRepository(CenterAPI, IODispatcher,CenterDao)
    }
}