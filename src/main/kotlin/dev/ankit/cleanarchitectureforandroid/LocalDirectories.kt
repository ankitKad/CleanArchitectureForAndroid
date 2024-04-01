package dev.ankit.cleanarchitectureforandroid

import ai.grazie.utils.capitalize
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import dev.ankit.cleanarchitectureforandroid.stubs.*
import java.io.File


///// DATA LAYER STARTS HERE
fun RecipeExecutor.setDataLayer(
    moduleTemplateData: ModuleTemplateData,
    hasRoom: Boolean,
    appName: String,
    addRootCheck: Boolean
) {
    createDirectory(moduleTemplateData.srcDir.resolve("data"))
    dataApi(moduleTemplateData, appName)
    dataLocal(moduleTemplateData)
    dataModel(moduleTemplateData)
    dataRepository(moduleTemplateData, appName)
    dataRoom(hasRoom, moduleTemplateData, appName)
    dataUtils(moduleTemplateData, appName, addRootCheck)
}

private fun RecipeExecutor.dataUtils(
    moduleTemplateData: ModuleTemplateData, appName: String, addRootCheck: Boolean
) {
    //This creates the utils folder inside data folder with necessary util functions
    createDirectory(moduleTemplateData.srcDir.resolve("data/utils"))
    save(
        emptyConstants(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("data/utils/Constants.kt")
    )
    save(
        emptyResource(moduleTemplateData.packageName), moduleTemplateData.srcDir.resolve("data/utils/Resource.kt")
    )
    save(
        emptyResponseToResource(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("data/utils/ResponseToResource.kt")
    )

    if(addRootCheck)
        save(
            emptyRootCheck(moduleTemplateData.packageName),
            moduleTemplateData.srcDir.resolve("data/utils/RootUtil.kt")
        )
}

private fun RecipeExecutor.dataRoom(
    hasRoom: Boolean, moduleTemplateData: ModuleTemplateData, appName: String
) {
    //This creates the room folder inside data folder, if flag is enabled and necessary doa, entity
    // and Db files
    if (hasRoom) {
        createDirectory(moduleTemplateData.srcDir.resolve("data/room"))
        createDirectory(moduleTemplateData.srcDir.resolve("data/room/dao"))
        save(
            emptyDao(moduleTemplateData.packageName), moduleTemplateData.srcDir.resolve("data/room/dao/DemoDao.kt")
        )
        createDirectory(moduleTemplateData.srcDir.resolve("data/room/entity"))
        save(
            emptyEntity(moduleTemplateData.packageName), moduleTemplateData.srcDir.resolve("data/room/entity/Demo.kt")
        )
        createDirectory(moduleTemplateData.srcDir.resolve("data/room/db"))
        save(
            emptyDatbase(moduleTemplateData.packageName, appName),
            moduleTemplateData.srcDir.resolve("data/room/db/${appName.capitalize()}Db.kt")
        )
    }
}

private fun RecipeExecutor.dataRepository(
    moduleTemplateData: ModuleTemplateData, appName: String
) {
    //This creates the repository folder inside data folder, and also creates datasource,
    // datasourceimpl folders and main repository impl file
    createDirectory(moduleTemplateData.srcDir.resolve("data/repository"))
    save(
        emptyMainRepositoryImpl(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("data/repository/MainRepositoryImpl.kt")
    )
    createDirectory(moduleTemplateData.srcDir.resolve("data/repository/datasource"))
    save(
        emptyRemoteDataSource(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("data/repository/datasource/${appName.capitalize()}RemoteDataSource.kt")
    )
    createDirectory(moduleTemplateData.srcDir.resolve("data/repository/datasourceimpl"))
    save(
        emptyRemoteDataSourceImpl(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("data/repository/datasourceimpl/${appName.capitalize()}RemoteDataSourceImpl.kt")
    )
}

private fun RecipeExecutor.dataModel(moduleTemplateData: ModuleTemplateData) {
    //This creates the model folder inside data folder
    createDirectory(moduleTemplateData.srcDir.resolve("data/model"))
}

private fun RecipeExecutor.dataLocal(moduleTemplateData: ModuleTemplateData) {
    //This creates the local folder inside data folder, and creates secured shared preference related files
    createDirectory(moduleTemplateData.srcDir.resolve("data/local"))
    save(
        emptyEncryptionHelper(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("data/local/EncryptionHelper.kt")
    )
    save(
        emptySecuredCryptoEncryptionHelper(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("data/local/SecuredCryptoEncryptionHelper.kt")
    )
    save(
        emptySecuredSharedPreferences(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("data/local/SecuredSharedPreferences.kt")
    )
    save(
        emptySecureSharedPreferencesImpl(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("data/local/SecureSharedPreferencesImpl.kt")
    )
}

private fun RecipeExecutor.dataApi(
    moduleTemplateData: ModuleTemplateData, appName: String
) {
    //This creates the api folder inside data folder, and creates NetworkService.kt file
    createDirectory(moduleTemplateData.srcDir.resolve("data/api"))
    save(
        emptyNetworkService(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("data/api/${appName}NetworkService.kt")
    )
}


///// DOMAIN LAYER STARTS HERE
fun RecipeExecutor.setDomainLayer(moduleTemplateData: ModuleTemplateData, appName: String) {
    createDirectory(moduleTemplateData.srcDir.resolve("domain"))
    domainRepository(moduleTemplateData)
    domainUseCase(moduleTemplateData)
}

private fun RecipeExecutor.domainUseCase(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("domain/usecase"))
    save(
        emptyUseCase(moduleTemplateData.packageName), moduleTemplateData.srcDir.resolve("domain/usecase/DemoUseCase.kt")
    )
}

private fun RecipeExecutor.domainRepository(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("domain/repository"))
    save(
        emptyRepository(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("domain/repository/MainRepository.kt")
    )
}


///// PRESENTATION LAYER STARTS HERE
fun RecipeExecutor.setPresentationLayer(moduleTemplateData: ModuleTemplateData, appName: String, shallAddRoom: Boolean) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation"))
    presentationAdapter(moduleTemplateData)
    presentationDi(moduleTemplateData, appName, shallAddRoom)
    presentationManager(moduleTemplateData)
    presentationView(moduleTemplateData)
    presentationViewModel(moduleTemplateData, appName)
    presentationViewModelFactory(moduleTemplateData)
    presentationLoader(moduleTemplateData)
}

private fun RecipeExecutor.presentationLoader(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/widget"))
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/widget/loader"))
    save(
        emptyLoaderFragment(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/widget/loader/LoaderFragment.kt")
    )
    save(
        emptyLoaderUtil(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/widget/loader/LoaderUtil.kt")
    )
}

private fun RecipeExecutor.presentationViewModelFactory(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/viewmodelfactory"))
    save(
        emptyViewModelFactory(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/viewmodelfactory/MainViewModelFactory.kt")
    )
}

private fun RecipeExecutor.presentationViewModel(
    moduleTemplateData: ModuleTemplateData,
    appName: String
) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/viewmodel"))
    save(
        emptyMainViewModel(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/viewmodel/MainViewModel.kt")
    )
    save(
        emptyNetworkStatusManagerViewModel(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/viewmodel/NetworkStatusViewModel.kt")
    )
}

private fun RecipeExecutor.presentationView(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/view"))
    save(
        emptyActivity(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/view/MainActivity.kt")
    )
}

private fun RecipeExecutor.presentationManager(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/manager"))
    save(
        emptyManager(moduleTemplateData.packageName),
        moduleTemplateData.srcDir.resolve("presentation/manager/NetworkStatusManager.kt")
    )
}

private fun RecipeExecutor.presentationDi(
    moduleTemplateData: ModuleTemplateData,
    appName: String,
    shallAddRoom: Boolean
) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/di"))
    save(
        emptyDoaDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/DaoModule.kt")
    )
    if (shallAddRoom)
        save(
            emptyDbDi(moduleTemplateData.packageName, appName),
            moduleTemplateData.srcDir.resolve("presentation/di/DatabaseModule.kt")
        )

    save(
        emptyDataSourceDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/DataSourceModule.kt")
    )
    save(
        emptyNetworkModuleDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/NetworkModule.kt")
    )
    save(
        emptyRepositoryDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/RepositoryModule.kt")
    )
    save(
        emptySharedPreferencesDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/SharedPrefsModule.kt")
    )
    save(
        emptyUseCaseDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/UseCaseModule.kt")
    )
    save(
        emptyViewModelFactoryModuleDi(moduleTemplateData.packageName, appName),
        moduleTemplateData.srcDir.resolve("presentation/di/ViewModelFactoryModule.kt")
    )
}

private fun RecipeExecutor.presentationAdapter(moduleTemplateData: ModuleTemplateData) {
    createDirectory(moduleTemplateData.srcDir.resolve("presentation/adapter"))
}

fun RecipeExecutor.generateManifestXml(manifestOut: File, packageName: String, appName: String) {
    mergeXml(
        emptyManifest(packageName, appName),
        manifestOut.resolve("AndroidManifest.xml")
    )
}

fun RecipeExecutor.generateModuleBuildGradle(
    moduleTemplateData: ModuleTemplateData,
    minApi: Int,
    shallAddRoom: Boolean,
    shallAddPinView: Boolean
) {
    copy(
        File(emptyModuleBuildGradle(moduleTemplateData.packageName, minApi, shallAddRoom, shallAddPinView)),
        moduleTemplateData.rootDir.resolve("build.gradle.kts")
    )
    save(
        emptyModuleBuildGradle(moduleTemplateData.packageName, minApi, shallAddRoom, shallAddPinView),
        moduleTemplateData.rootDir.resolve("build.gradle.kts")
    )
}

fun RecipeExecutor.generateActivityLayouts(
    moduleTemplateData: ModuleTemplateData
) {
    mergeXml(
        emptyMainActivityLayout(),
        moduleTemplateData.resDir.resolve("layout/activity_main.xml")
    )

    mergeXml(
        emptyLauncherActivityLayout(),
        moduleTemplateData.resDir.resolve("layout/activity_launcher.xml")
    )
    mergeXml(
        emptyLoaderFragment(),
        moduleTemplateData.resDir.resolve("layout/fragment_loader.xml")
    )
    mergeXml(
        emptyLoaderBg(),
        moduleTemplateData.resDir.resolve("drawable/loader_bg.xml")
    )

    mergeXml(
        emptyThemesXml(),
        moduleTemplateData.resDir.resolve("values/themes.xml")
    )
    mergeXml(
        emptyColoursXml(),
        moduleTemplateData.resDir.resolve("values/colors.xml")
    )
}