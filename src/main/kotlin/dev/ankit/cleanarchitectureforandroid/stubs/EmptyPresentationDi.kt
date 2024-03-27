package dev.ankit.cleanarchitectureforandroid.stubs

import ai.grazie.utils.capitalize

fun emptyDoaDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.data.room.dao.DemoDao
    import $packageName.data.room.db.${appName.capitalize()}Db
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class DaoModule {

        //Following is one of the example
        @Provides
        @Singleton
        fun provideDemoDao(${appName.lowercase()}Db: ${appName.capitalize()}Db): DemoDao = ${appName.lowercase()}Db.demoDao()
    }
""".trimIndent()


fun emptyDbDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import android.content.Context
    import $packageName.data.room.db.${appName.capitalize()}Db
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.android.qualifiers.ApplicationContext
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class DatabaseModule {

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): ${appName.capitalize()}Db {
            return ${appName.capitalize()}Db.buildEncryptedDb(context)
        }

    }
""".trimIndent()

fun emptyDataSourceDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.data.api.${appName.capitalize()}NetworkService
    import $packageName.data.repository.datasource.${appName.capitalize()}RemoteDataSource
    import $packageName.data.repository.datasourceimpl.${appName.capitalize()}RemoteDataSourceImpl
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class DataSourceModule {

        @Singleton
        @Provides
        fun provide${appName.capitalize()}RemoteDataSource(networkService: ${appName.capitalize()}NetworkService): ${appName.capitalize()}RemoteDataSource = ${appName.capitalize()}RemoteDataSourceImpl(networkService)

    }
""".trimIndent()

fun emptyNetworkModuleDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.BuildConfig
    import $packageName.data.api.${appName.capitalize()}NetworkService
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import okhttp3.CertificatePinner
    import okhttp3.OkHttpClient
    import okhttp3.Request
    import okhttp3.logging.HttpLoggingInterceptor
    import retrofit2.Retrofit
    import retrofit2.converter.gson.GsonConverterFactory
    import java.util.concurrent.TimeUnit
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class NetworkModule {

        //Following is the way to pin certificate, go to ssllabs to generate one
        private val certificatePinning = CertificatePinner.Builder()
            //.add("jsonplaceholder.typicode.com", "sha256/JCmeBpzLgXemYfoqqEoVJlU/givddwcfIXpwyaBk52I=")
            .build()


        @Provides
        @Singleton
        fun provideRetrofitInstance(): Retrofit {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient
                .Builder()
                .certificatePinner(certificatePinning)
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder().addHeader("accept", "application/json").build()
                    return@addInterceptor chain.proceed(request)
                }
                .build()
            //You can add baseUrl directly from BuildConfig
            return Retrofit.Builder().baseUrl(BuildConfig.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
        }

        @Provides
        @Singleton
        fun provideNetworkService(retrofit: Retrofit): ${appName.capitalize()}NetworkService =
            retrofit.create(${appName.capitalize()}NetworkService::class.java)

    }
""".trimIndent()

fun emptyRepositoryDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.data.repository.MainRepositoryImpl
    import $packageName.data.repository.datasource.${appName.capitalize()}RemoteDataSource
    import $packageName.domain.repository.MainRepository
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class RepositoryModule {

        @Provides
        @Singleton
        fun providesMainRepository(${appName.lowercase()}RemoteDataSource: ${appName.capitalize()}RemoteDataSource): MainRepository =
            MainRepositoryImpl(${appName.lowercase()}RemoteDataSource)
    }
""".trimIndent()

fun emptySharedPreferencesDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import android.content.Context
    import $packageName.data.local.SecureSharedPreferencesImpl
    import $packageName.data.local.SecuredCryptoEncryptionHelper
    import $packageName.data.local.SecuredSharedPreferences
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.android.qualifiers.ApplicationContext
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class SharedPrefsModule {

        @Provides
        @Singleton
        fun provideSecuredCryptoEncryptionHelper(@ApplicationContext context: Context): SecuredCryptoEncryptionHelper = SecuredCryptoEncryptionHelper(context)

        @Provides
        @Singleton
        fun provideSecureSharedPreferences(@ApplicationContext context: Context,securedCryptoEncryptionHelper: SecuredCryptoEncryptionHelper): SecuredSharedPreferences = SecureSharedPreferencesImpl(context, securedCryptoEncryptionHelper)

    }
""".trimIndent()

fun emptyUseCaseDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.domain.repository.MainRepository
    import $packageName.domain.usecase.DemoUseCase
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class UseCaseModule {

        @Provides
        @Singleton
        fun provideDemosUseCase(mainRepository: MainRepository): DemoUseCase = DemoUseCase(mainRepository)
    }
""".trimIndent()

fun emptyViewModelFactoryModuleDi(
    packageName: String,
    appName: String
) = """
    package $packageName.presentation.di

    import $packageName.domain.usecase.DemoUseCase
    import $packageName.presentation.viewmodelfactory.MainViewModelFactory
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    class ViewModelFactoryModule {

        @Provides
        @Singleton
        fun providesMainViewModelFactory(demoUseCase: DemoUseCase): MainViewModelFactory = MainViewModelFactory(demoUseCase)
    }
""".trimIndent()