package com.sph.mobdatausage.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sph.mobdatausage.BuildConfig
import com.sph.mobdatausage.exts.hasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val BASE_URL = "https://data.gov.sg/api/action/"
private const val CACHE_CONTROL = "Cache-Control"

@Module(includes = [AppModule::class])
class NetworkModule {

    @Provides
    internal fun provideRetrofitWithCache(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    internal fun provideOkHttpClientWithCache(
            context: Context,
            httpLoggingInterceptor: HttpLoggingInterceptor,
            cache: Cache,
            @Named("cache-interceptor") interceptor: Interceptor,
            @Named("cache-network-interceptor") networkInterceptor: Interceptor): OkHttpClient {

        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)
        httpClientBuilder.addInterceptor(interceptor)
        httpClientBuilder.addNetworkInterceptor(networkInterceptor)
        httpClientBuilder.cache(cache)

        return httpClientBuilder.build()
    }

    @Provides
    internal fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024.toLong()
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS
        else HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Named("cache-network-interceptor")
    internal fun provideNetworkInterceptorWithCache(): Interceptor {
        return Interceptor { chain ->
            chain.proceed(chain.request()).newBuilder()
                    .removeHeader("pragma")
                    .header(CACHE_CONTROL,
                            CacheControl.Builder()
                                    .maxAge(5, TimeUnit.MINUTES).build().toString()).build()
        }

    }

    @Provides
    @Named("cache-interceptor")
    internal fun provideInterceptorWithCache(context: Context): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            if (!hasNetwork(context)) {
                request.newBuilder()
                        .header(CACHE_CONTROL, CacheControl.Builder().maxStale(3,
                                TimeUnit.DAYS)
                                .build().toString())
                        .removeHeader("pragma")
                        .build()

            }
            chain.proceed(request)
        }
    }
}
