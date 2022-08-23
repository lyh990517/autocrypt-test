package yunho.app.autocrypt.DI

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import yunho.app.autocrypt.Data.LocalDB.provideCenterDB
import yunho.app.autocrypt.Data.LocalDB.provideCenterDao
import yunho.app.autocrypt.Data.RemoteDB.buildOkHttpClient
import yunho.app.autocrypt.Data.RemoteDB.provideCenterAPIService
import yunho.app.autocrypt.Data.RemoteDB.provideCenterRetrofit
import yunho.app.autocrypt.Data.Repository.CenterRepository
import yunho.app.autocrypt.Presentation.ViewModel.MainViewModel
import yunho.app.autocrypt.Presentation.ViewModel.MapViewModel

internal val AppModule = module {
    //dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //retrofit
    single { buildOkHttpClient() }
    single { provideCenterRetrofit(get()) }
    single { provideCenterAPIService(get()) }

    //repository
    single { CenterRepository(get(),get(),get()) }

    //viewModel
    viewModel { MainViewModel(get()) }
    viewModel { MapViewModel(get()) }

    //database
    single { provideCenterDB(androidContext()) }
    single { provideCenterDao(get()) }

}