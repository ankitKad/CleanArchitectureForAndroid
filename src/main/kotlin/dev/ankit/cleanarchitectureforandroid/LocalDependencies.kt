import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addSecretsGradlePlugin

fun RecipeExecutor.addCoroutinesDependencies(coroutineVersion: String = "1.7.3"){
    addDependency(mavenCoordinate = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    addDependency(mavenCoordinate = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
}

fun RecipeExecutor.addSplashScreenApiDependencies(splashScreenAPiVersion: String = "1.0.1"){
    addDependency(mavenCoordinate = "androidx.core:core-splashscreen:$splashScreenAPiVersion")
}

fun RecipeExecutor.addNavDependencies(navVersion: String = "2.7.6") {
    addDependency(mavenCoordinate = "androidx.navigation:navigation-fragment-ktx:$navVersion")
    addDependency(mavenCoordinate = "androidx.navigation:navigation-ui-ktx:$navVersion")
    applyPlugin("androidx.navigation.safeargs.kotlin", navVersion)
}

fun RecipeExecutor.addPinViewDependencies(pinViewVersion: String = "1.4.4"){
    addDependency(mavenCoordinate = "io.github.chaosleung:pinview:$pinViewVersion")
}


fun RecipeExecutor.addRetrofitDependencies(retrofitVersion: String = "2.9.0") {
    addDependency(mavenCoordinate = "com.squareup.retrofit2:retrofit:$retrofitVersion")
    addDependency(mavenCoordinate = "com.squareup.retrofit2:converter-gson:$retrofitVersion")
}

fun RecipeExecutor.addHiltDependencies(hiltVersion: String = "2.50") {
    applyPlugin("com.google.dagger.hilt.android", hiltVersion)
    addDependency(mavenCoordinate = "com.google.dagger:hilt-compiler:$hiltVersion")
    addDependency(mavenCoordinate = "com.google.dagger:hilt-android:$hiltVersion")
}

fun RecipeExecutor.addLifecycleDependencies(lifecycleVersion: String = "2.6.2") {
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    addDependency(mavenCoordinate = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
}

fun RecipeExecutor.addEncryptedSharedPrefsDependencies(cryptoVersion: String = "1.0.0"){
    addDependency(mavenCoordinate = "androidx.security:security-crypto:$cryptoVersion")
}

fun RecipeExecutor.addOkHttpDependencies(okHttpVersion: String = "4.11.0"){
    addDependency("com.squareup.okhttp3:okhttp:$okHttpVersion")
    addDependency("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

}

fun RecipeExecutor.addRoomDependencies(roomVersion: String = "2.6.1", cipherVersion: String = "4.5.3") {
    //addDependency(mavenCoordinate = "androidx.room:room-compiler:$roomVersion")
    addDependency(mavenCoordinate = "androidx.room:room-runtime:$roomVersion")
    addDependency(mavenCoordinate = "androidx.room:room-ktx:$roomVersion")
    //addDependency(mavenCoordinate = "androidx.room:room-compiler:$roomVersion", configuration = "annotationProcessor")
    addDependency(mavenCoordinate = "net.zetetic:android-database-sqlcipher:$cipherVersion")
}

fun RecipeExecutor.addGlideDependencies(glideVersion: String = "4.16.0"){
    addDependency(mavenCoordinate = "com.github.bumptech.glide:glide:$glideVersion")
}

fun RecipeExecutor.addClassPathDependencies(navVersion: String = "2.7.6"){
    addClasspathDependency("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")

}

fun RecipeExecutor.setBuildFeature(){
    setBuildFeature("dataBinding", true)
    setBuildFeature("viewBinding", true)
    setBuildFeature("buildConfig", true)
}