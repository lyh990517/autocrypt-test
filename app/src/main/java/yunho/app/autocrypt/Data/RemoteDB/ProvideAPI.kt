package yunho.app.autocrypt.Data.RemoteDB

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yunho.app.autocrypt.BuildConfig
import java.util.concurrent.TimeUnit

internal fun provideCenterAPIService(retrofit: Retrofit): CenterService =
    retrofit.create(CenterService::class.java)

internal fun provideCenterRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit = Retrofit.Builder()
    .baseUrl(URL.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

internal fun buildOkHttpClient() : OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}