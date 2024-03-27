package dev.ankit.cleanarchitectureforandroid.stubs

import java.io.File

fun emptyModuleBuildGradle(
    packageName: String,
    minApi: Int,
    shallAddRoom: Boolean,
    shallAddPinView: Boolean,
    roomVersion: String = "$".plus("roomVersion"),
    coroutineVersion: String = "$".plus("coroutineVersion"),
    navigationVersion: String = "$".plus("navigationVersion"),
    retrofitVersion: String = "$".plus("retrofitVersion"),
    daggerHiltVersion: String = "$".plus("daggerHiltVersion"),
    lifecycleVersion: String = "$".plus("lifecycleVersion"),
    cryptoVersion: String = "$".plus("cryptoVersion"),
    okHttpVersion: String = "$".plus("okHttpVersion"),
    cipherVersion: String = "$".plus("cipherVersion"),
    circleImageView: String = "$".plus("circleImageView"),
    glide: String = "$".plus("glide"),
    coilVersion: String = "$".plus("coilVersion"),
) : String {

    val addRoomDependency = if (shallAddRoom) """
                /**
                 * Room components
                 */
                val roomVersion = "2.6.1"
                implementation("androidx.room:room-runtime:$roomVersion")
                kapt("androidx.room:room-compiler:$roomVersion")
                implementation("androidx.room:room-ktx:$roomVersion")
    """.trimIndent() else ""

    val addPinViewDependency = if (shallAddPinView) """
        /**
         * Pinview component
         */
        implementation ("io.github.chaosleung:pinview:1.4.4")
    """.trimIndent() else ""

   return """
    plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("kotlin-kapt")
        id("androidx.navigation.safeargs.kotlin")
        id("com.google.dagger.hilt.android")
    }

    android {
        namespace = "$packageName"
        compileSdk = 34

        defaultConfig {
            applicationId = "$packageName"
            minSdk = $minApi
            targetSdk = 34
            versionCode = 1
            versionName = "1.0.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        flavorDimensions.add("environment")

        productFlavors {
            create("staging") {
                dimension = "environment"
                applicationId = "$packageName.staging"
                minSdk = $minApi
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"
                buildConfigField("String","baseUrl", "\"" + properties["endpoint"] + "\"")

            }

            create("preprod") {
                dimension = "environment"
                applicationId = "$packageName.preprod"
                minSdk = $minApi
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"
                buildConfigField("String","baseUrl", "\"" + properties["endpoint"] + "\"")
            }

            create("prod") {
                dimension = "environment"
                applicationId = "$packageName"
                minSdk = $minApi
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"
                buildConfigField("String","baseUrl", "\"" + properties["endpoint"] + "\"")
            }

        }

        buildTypes {
            debug {
                isMinifyEnabled = false
                isShrinkResources = false
            }

            release {
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = "1.8"
        }

        buildFeatures {
            dataBinding = true
            viewBinding = true
            buildConfig = true
            //compose = true
        }

        //composeOptions {
        //kotlinCompilerExtensionVersion = "1.5.3"
        //}
    }

    dependencies {

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.11.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")

        /**
         * Firestore components
         */
        //implementation("com.google.firebase:firebase-firestore:24.10.0")
        //implementation("com.google.firebase:firebase-auth-ktx:22.3.0")

        /**
         * Coroutines implementation
         */
        val coroutineVersion = "1.7.3"
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

        /**
         * Splash Screen API component
         */
        implementation ("androidx.core:core-splashscreen:1.0.1")

        /**
         * Navigation components
         */
        val navigationVersion = "2.7.6"
        implementation ("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
        implementation ("androidx.navigation:navigation-ui-ktx:$navigationVersion")

        $addPinViewDependency

        /**
         * Retrofit components
         */
        val retrofitVersion = "2.9.0"
        implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
        implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

        /**
         * Dagger-Hilt component
         */
        val  daggerHiltVersion = "2.50"
        implementation ("com.google.dagger:hilt-android:$daggerHiltVersion")
        kapt ("com.google.dagger:hilt-compiler:$daggerHiltVersion")

        /**
         * Lifecycle components
         */
        val lifecycleVersion = "2.6.2"
        implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
        implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

        /**
         * Encryption/Decryption components
         */
        val cryptoVersion = "1.0.0"
        implementation ("androidx.security:security-crypto:$cryptoVersion")

        /**
         * Logging interceptor components
         */
        val okHttpVersion = "4.11.0"
        implementation ("com.squareup.okhttp3:okhttp:$okHttpVersion")
        implementation ("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

        $addRoomDependency

        /**
         * Encrypted room component
         */
        val cipherVersion = "4.5.3"
        implementation ("net.zetetic:android-database-sqlcipher:$cipherVersion")


        /**
         * Compose related component
         */
        //implementation("androidx.activity:activity-compose:1.8.2")
        //implementation("androidx.compose.ui:ui:1.6.1")
        //implementation("androidx.compose.material3:material3:1.2.0")
        //implementation ("androidx.compose.ui:ui-tooling-preview")
        //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        //debugImplementation ("androidx.compose.ui:ui-tooling")

        /**
         * Circle image view component
         */
        //val circleImageView = "3.1.0"
        //implementation("de.hdodenhof:circleimageview:$circleImageView")

        /**
         * Glide component
         */
        val glide = "4.16.0"
        implementation("com.github.bumptech.glide:glide:$glide")

        /**
         * Coil component
         */
        //val coilVersion = "2.6.0"
        //implementation("io.coil-kt:coil-compose:$coilVersion")

        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    }

    kapt {
        correctErrorTypes = true
    }
""".trimIndent()
}