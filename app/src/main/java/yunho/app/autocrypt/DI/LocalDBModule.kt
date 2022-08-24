package yunho.app.autocrypt.DI

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yunho.app.autocrypt.Data.LocalDB.CenterDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {


    @Singleton
    @Provides
    fun provideCenterDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CenterDatabase::class.java,
        CenterDatabase.DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideCenterDao(centerDatabase: CenterDatabase) = centerDatabase.CenterDao()
}