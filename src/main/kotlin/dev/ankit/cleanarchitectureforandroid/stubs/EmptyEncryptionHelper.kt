package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyEncryptionHelper(
    packageName: String
) = """
    package ${packageName}.data.local

    interface EncryptionHelper {
        fun encrypt(key:String, data: String)
        fun decrypt(key:String): String
    }
""".trimIndent()