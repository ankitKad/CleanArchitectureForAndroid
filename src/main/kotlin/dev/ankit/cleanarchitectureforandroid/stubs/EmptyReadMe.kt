package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyReadMeText(): String {
    val navVersion = "$".plus("navVersion")
    return """
    1. Keep Minimum SDK 24
    2. Select kotlin DSL as Build Configuration Language
    3. Add below code to project level build.gradle.kts file
    // Top-level build file where you can add configuration options common to all sub-projects/modules.
    buildscript {
        repositories {
            google()
        }
        dependencies {
            val navVersion = "2.7.6"
            classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        }
    }
    plugins {
        id("com.android.application") version "8.2.0" apply false
        id("org.jetbrains.kotlin.android") version "1.9.10" apply false
        id("com.google.dagger.hilt.android") version "2.50" apply false
    }
    4. Add this line in Project level gradle.properties file
    stagingendpoint=https://com.example.in/
    preprodendpoint=https://com.example.in/
    prodendpoint=https://com.example.in/
    5. You can also enable/disable kotlinOptions accordingly based on your gradle plugin config.
    6. Rebuild project and Run.
    7. After successful run, delete build.gradle.kts for module which is throwing error, not the build.gradle.
""".trimIndent()
}