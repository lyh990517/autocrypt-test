package yunho.app.autocrypt.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yunho.app.autocrypt.Application
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltDIModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context) : Application {
        return app as Application
    }
}