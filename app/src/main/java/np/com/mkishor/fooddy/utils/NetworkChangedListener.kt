package np.com.mkishor.fooddy.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * @author:  Kishor Mainali
 * @date:  03/01/2021 12:33
 * @email:  mainalikishor@outlook.com
 *
 */
class NetworkChangedListener : ConnectivityManager.NetworkCallback() {


    private val isNetworkAvailable = MutableStateFlow(false)

    fun checkNetworkAvailability(context: Context): MutableStateFlow<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
            connectivityManager.allNetworks.forEach { network ->
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                networkCapabilities?.let {
                    if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                        isConnected = true
                        return@forEach
                    }
                }
            }
        } else {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), this)
            connectivityManager.allNetworkInfo.forEach { networkInfo ->
                networkInfo?.let {
                    if (it.isAvailable && it.isConnected) {
                        isConnected = true
                        return@forEach
                    }
                }
            }
        }
        isNetworkAvailable.value = isConnected
        return isNetworkAvailable
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isNetworkAvailable.value = true
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        isNetworkAvailable.value = false
    }
}