package yunho.app.autocrypt.DI

import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import yunho.app.autocrypt.Data.RemoteDB.buildOkHttpClient
import yunho.app.autocrypt.Data.RemoteDB.provideCenterAPIService
import yunho.app.autocrypt.Data.RemoteDB.provideCenterRetrofit
import yunho.app.autocrypt.Data.Repository.CenterRepository
import yunho.app.autocrypt.Data.Repository.Repository
import yunho.app.autocrypt.Presentation.ViewModel.MainViewModel

internal val AppModule = module {
    //dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    //retrofit
    single { buildOkHttpClient() }
    single { provideCenterRetrofit(get()) }
    single { provideCenterAPIService(get()) }

    //repository
    single { CenterRepository(get(),get()) }

    //viewModel
    viewModel { MainViewModel(get()) }


}