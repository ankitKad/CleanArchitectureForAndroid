package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyMainViewModel(
    packageName: String
) = """
    package $packageName.presentation.viewmodel

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import $packageName.data.utils.Resource
    import $packageName.domain.usecase.DemoUseCase
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.delay
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class MainViewModel @Inject constructor(private val demoUseCase: DemoUseCase): ViewModel() {
        //private val chatsMutable: MutableLiveData<Resource<ChatsResponse>> = MutableLiveData()
        //val chats: LiveData<Resource<ChatsResponse>> get() = chatsMutable

        //fun getChats() = viewModelScope.launch {
        //    chatsMutable.postValue(Resource.Loading())
        //    delay(2000)
        //    val chatResponse = getChatsUseCase.execute()
        //    chatsMutable.postValue(chatResponse)
        //}

    }
""".trimIndent()

fun emptyNetworkStatusManagerViewModel(
    packageName: String
) = """
    package $packageName.presentation.viewmodel

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.ViewModel
    import $packageName.presentation.manager.NetworkStatus
    import $packageName.presentation.manager.NetworkStatusManager
    import dagger.hilt.android.lifecycle.HiltViewModel
    import javax.inject.Inject

    @HiltViewModel
    class NetworkStatusViewModel @Inject constructor(private val networkStatusManager: NetworkStatusManager) :
        ViewModel() {
        private val _networkStatus = networkStatusManager.networkStatus
        val networkStatus: LiveData<NetworkStatus> get() = _networkStatus
    }
""".trimIndent()