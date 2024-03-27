package dev.ankit.cleanarchitectureforandroid.stubs

import ai.grazie.utils.capitalize

fun emptyMainRepositoryImpl(
    packageName: String,
    appName: String
) = """
    package ${packageName}.data.repository

    //import ${packageName}.data.model.main.ChatsResponse
    import ${packageName}.data.repository.datasource.${appName}RemoteDataSource
    //import ${packageName}.data.utils.Resource
    //import ${packageName}.data.utils.ResponseToResource
    import ${packageName}.domain.repository.MainRepository

    class MainRepositoryImpl(private val ${appName.lowercase()}RemoteDataSource: ${appName}RemoteDataSource) :
        MainRepository {

            //override suspend fun getChats(): Resource<ChatsResponse> =
            //ResponseToResource.responseToResource(${appName.lowercase()}RemoteDataSource.getChats())

    }
""".trimIndent()

fun emptyRemoteDataSource(
    packageName: String,
    appName: String
) = """
    package $packageName.data.repository.datasource

    //import $packageName.data.model.main.ChatsResponse
    import retrofit2.Response

    interface ${appName.capitalize()}RemoteDataSource {
        //suspend fun getChats(): Response<ChatsResponse>
    }
""".trimIndent()

fun emptyRemoteDataSourceImpl(
    packageName: String,
    appName: String
) = """
    package $packageName.data.repository.datasourceimpl

    import $packageName.data.api.${appName.capitalize()}NetworkService
    //import $packageName.data.model.main.ChatsResponse
    import $packageName.data.repository.datasource.${appName.capitalize()}RemoteDataSource
    import retrofit2.Response

    class ${appName.capitalize()}RemoteDataSourceImpl(private val networkService: ${appName.capitalize()}NetworkService): ${appName.capitalize()}RemoteDataSource {

        //override suspend fun getChats(): Response<ChatsResponse> = networkService.getChat()
    }
""".trimIndent()