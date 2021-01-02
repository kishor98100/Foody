package np.com.mkishor.fooddy.utils


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 18:16
 * @email:  mainalikishor@outlook.com
 *
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()

}