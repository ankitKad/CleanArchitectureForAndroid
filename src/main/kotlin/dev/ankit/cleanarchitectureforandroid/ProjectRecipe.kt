import ai.grazie.utils.capitalize
import com.android.tools.idea.npw.module.recipes.addTestDependencies
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.addMaterialDependency
import com.android.tools.idea.wizard.template.impl.activities.emptyActivity.src.emptyActivityKt
import dev.ankit.cleanarchitectureforandroid.*
import dev.ankit.cleanarchitectureforandroid.stubs.emptyApplication
import dev.ankit.cleanarchitectureforandroid.stubs.emptyGradleProperties
import dev.ankit.cleanarchitectureforandroid.stubs.emptyModuleBuildGradle
import dev.ankit.cleanarchitectureforandroid.stubs.emptyModuleBuildGradleWidOut
import dev.ankit.cleanarchitectureforandroid.stubs.presentation.emptyLauncherActivity
import java.io.File

fun RecipeExecutor.generateEmptyActivityWithCA(
    moduleTemplateData: ModuleTemplateData,
    shallAddRoom: Boolean,
    shallAddPinView: Boolean,
    packageName: String,
    appName: String,
    minApi: Int
) {

    val (projectData, srcOut, resOut, manifestOut) = moduleTemplateData

    save(
        emptyModuleBuildGradleWidOut(moduleTemplateData.packageName,minApi, shallAddRoom, shallAddPinView),
        moduleTemplateData.rootDir.resolve("build.gradle")
    )

    /*save(
        emptyGradleProperties(),
        moduleTemplateData.rootDir.resolve("gradle.properties")
    )*/

    /*generateModuleBuildGradle(moduleTemplateData,minApi,shallAddRoom, shallAddPinView)*/
    /*emptyModuleBuildGradle(moduleTemplateData.packageName,minApi,shallAddRoom, shallAddPinView)*/

    /*addMaterialDependency(projectData.androidXSupport)*/
    /*generateManifest(
        moduleTemplateData,
        activityClass,
        packageName,
        isLauncher,
        hasNoActionBar = false,
        generateActivityTitle = true
    )*/

    /**
     * Generic dependencies added here
     */
    /*addAllKotlinDependencies(moduleTemplateData, revision = "1.9.10")
    addTestDependencies()*/

    /**
     * Dependencies related to Clean Architecture added here
     */
    /*addCoroutinesDependencies()
    addSplashScreenApiDependencies()
    addNavDependencies()
    if (shallAddPinView) addPinViewDependencies()
    addRetrofitDependencies()
    addHiltDependencies()
    addLifecycleDependencies()
    addEncryptedSharedPrefsDependencies()
    addOkHttpDependencies()
    if (shallAddRoom) addRoomDependencies()
    addGlideDependencies()*/

    /**
     * Classpath dependencies added here
     */
    //addClassPathDependencies()

    /**
     * Set build feature
     */
    /*setBuildFeature()

    requireJavaVersion("1.8", true)*/

    /**
     * Set src folder structure
     */
    setDataLayer(moduleTemplateData, shallAddRoom, appName)
    setDomainLayer(moduleTemplateData, appName)
    setPresentationLayer(moduleTemplateData, appName)

    generateManifestXml(manifestOut, packageName, appName)

    generateActivityLayouts(moduleTemplateData)

    //generateModuleBuildGradle(moduleTemplateData,minApi,shallAddRoom, shallAddPinView)


    save(
        emptyApplication(packageName, appName),
        moduleTemplateData.srcDir.resolve("${appName.capitalize()}Application.kt")
    )
    save(
        emptyLauncherActivity(packageName, appName),
        moduleTemplateData.srcDir.resolve("LauncherActivity.kt")
    )


    /*copy(
        File(emptyModuleBuildGradle(moduleTemplateData.packageName,minApi, shallAddRoom, shallAddPinView)),
        moduleTemplateData.rootDir.resolve("build.gradle.kts")
    )*/



    open(moduleTemplateData.srcDir.resolve("${appName.capitalize()}Application.kt"))

}