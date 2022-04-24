package hu.bme.aut.shopper.di

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import hu.bme.aut.shopper.BuildConfig
import hu.bme.aut.shopper.network.ShoppingListAPI

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(" https://api.todoist.com/rest/v1")
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    if (BuildConfig.DEBUG) {
                        Log.d("HttpLoggingInterceptor", message)
                    }
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    fun shoppingListApi(retrofit: Retrofit): ShoppingListAPI = retrofit.create()

}