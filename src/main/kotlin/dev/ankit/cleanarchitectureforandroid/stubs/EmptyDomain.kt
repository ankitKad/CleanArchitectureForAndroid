package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyRepository(
    packageName: String
) = """
    package ${packageName}.domain.repository

    import ${packageName}.data.utils.Resource

    interface MainRepository {
        //Following is one of the example
        //suspend fun getChats(): Resource<ChatsResponse>
    }
""".trimIndent()

fun emptyUseCase(
    packageName: String
) = """
    package ${packageName}.domain.usecase

    import ${packageName}.data.utils.Resource
    import ${packageName}.domain.repository.MainRepository
    import retrofit2.Response

    class DemoUseCase(private val mainRepository: MainRepository) {
    
        //suspend fun execute():Resource<ChatsResponse> = mainRepository.getChats()
    }
""".trimIndent()