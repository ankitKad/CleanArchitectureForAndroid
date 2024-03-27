import ai.grazie.utils.capitalize
import com.android.tools.idea.npw.module.recipes.addTestDependencies
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.addMaterialDependency
import com.android.tools.idea.wizard.template.impl.activities.emptyActivity.src.emptyActivityKt
import dev.ankit.cleanarchitectureforandroid.*
import dev.ankit.cleanarchitectureforandroid.stubs.*
import dev.ankit.cleanarchitectureforandroid.stubs.presentation.emptyLauncherActivity
import java.io.File

fun RecipeExecutor.generateEmptyActivityWithCA(
    moduleTemplateData: ModuleTemplateData,
    shallAddRoom: Boolean,
    shallAddPinView: Boolean,
    packageName: String,
    appName: String,
    minApi: Int,
    disableScreenshot: Boolean
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
    setPresentationLayer(moduleTemplateData, appName, shallAddRoom)

    generateManifestXml(manifestOut, packageName, appName)

    generateActivityLayouts(moduleTemplateData)

    //generateModuleBuildGradle(moduleTemplateData,minApi,shallAddRoom, shallAddPinView)


    save(
        emptyApplication(packageName, appName, disableScreenshot),
        moduleTemplateData.srcDir.resolve("${appName.capitalize()}Application.kt")
    )
    save(
        emptyLauncherActivity(packageName, appName),
        moduleTemplateData.srcDir.resolve("LauncherActivity.kt")
    )

    save(
        emptyReadMeText(),
        moduleTemplateData.srcDir.resolve("readme.txt")
    )

    open(moduleTemplateData.srcDir.resolve("${appName.capitalize()}Application.kt"))

    open(moduleTemplateData.srcDir.resolve("readme.txt"))

}