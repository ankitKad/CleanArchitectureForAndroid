package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyNetworkService(
    packageName: String,
    className: String
) = """
package ${packageName}.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ${className}NetworkService {
    //Following is one of the example to implement api call function
    //@GET("chatlist")
    //suspend fun getChat(): Response<ChatsResponse>

}
"""