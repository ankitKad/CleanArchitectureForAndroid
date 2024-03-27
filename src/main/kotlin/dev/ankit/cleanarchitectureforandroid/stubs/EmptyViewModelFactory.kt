package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyViewModelFactory(
    packageName: String
) = """
    package $packageName.presentation.viewmodelfactory

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import $packageName.domain.usecase.DemoUseCase
    import $packageName.presentation.viewmodel.MainViewModel

    class MainViewModelFactory(private val demoUseCase: DemoUseCase): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(demoUseCase) as T
        }
    }
""".trimIndent()