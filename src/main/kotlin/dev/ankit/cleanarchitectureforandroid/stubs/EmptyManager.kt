package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyManager(
    packageName: String
) = """
    package $packageName.presentation.manager


    import android.content.BroadcastReceiver
    import android.content.Context
    import android.content.Intent
    import android.net.ConnectivityManager
    import android.net.Network
    import android.net.NetworkCapabilities
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import dagger.hilt.android.qualifiers.ApplicationContext
    import javax.inject.Inject

    class NetworkStatusManager @Inject constructor(@ApplicationContext private val context: Context) {

        private val _networkStatus = MutableLiveData<NetworkStatus>()
        val networkStatus: LiveData<NetworkStatus> get() = _networkStatus

        init {
            registerNetworkCallback()
        }

        private fun registerNetworkCallback() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postNetworkStatus(NetworkStatus.Connected)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    postNetworkStatus(NetworkStatus.Disconnected)
                }
            })
        }

        private fun postNetworkStatus(status: NetworkStatus) {
            _networkStatus.postValue(status)
        }

        inner class NetworkChangeReceiver : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                    val networkStatus =
                        if (isConnected()) NetworkStatus.Connected else NetworkStatus.Disconnected
                    postNetworkStatus(networkStatus)
                }
            }
        }

        private fun isConnected(): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        }
    }

    sealed class NetworkStatus {
        data object Connected : NetworkStatus()
        data object Disconnected : NetworkStatus()
    }
""".trimIndent()