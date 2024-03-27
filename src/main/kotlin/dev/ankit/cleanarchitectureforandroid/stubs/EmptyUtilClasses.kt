package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyConstants(
    packageName: String,
    appName: String
) = """
    package $packageName.data.utils

    object Constants {
        const val encryptionKey = "${appName.lowercase()}_encryption_key"
    }
""".trimIndent()

fun emptyResource(
    packageName: String
) = """
    package $packageName.data.utils

    sealed class Resource<T>(
        val data : T? = null,
        val message: String? = null
    ) {
        class Success<T>(data: T?) : Resource<T>(data)
        class Loading<T>(data: T? = null) : Resource<T>(data)
        class Error<T>(message: String, data: T? = null) : Resource<T>(data,message)
    }
""".trimIndent()

fun emptyResponseToResource(
    packageName: String
) = """
    package $packageName.data.utils

    import retrofit2.Response

    object ResponseToResource {
        inline fun <reified T : Any> responseToResource(response: Response<T>): Resource<T> {
            return if (response.isSuccessful) {
                response.body()?.let { result ->
                    Resource.Success(result)
                } ?: run {
                    Resource.Error("Response body is null")
                }
            } else {
                Resource.Error(response.message())
            }
        }

    }
""".trimIndent()