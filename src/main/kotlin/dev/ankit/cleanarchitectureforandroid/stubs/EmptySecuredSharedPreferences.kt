package dev.ankit.cleanarchitectureforandroid.stubs

fun emptySecuredSharedPreferences(
    packageName: String
) = """
    package ${packageName}.data.local

    interface SecuredSharedPreferences {
        fun saveString(key: String, data: String)
        fun getString(key: String): String
    }
""".trimIndent()