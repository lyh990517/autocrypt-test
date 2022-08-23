package yunho.app.autocrypt

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import yunho.app.autocrypt.DI.AppModule

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            androidLogger(Level.ERROR)
            modules(AppModule)
        }
    }
}