package np.com.mkishor.fooddy.data.repositories

import dagger.hilt.android.scopes.ActivityRetainedScoped
import np.com.mkishor.fooddy.data.sources.local.LocalDataSource
import np.com.mkishor.fooddy.data.sources.remote.RemoteDataSource
import javax.inject.Inject


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 18:13
 * @email:  mainalikishor@outlook.com
 *
 */
@ActivityRetainedScoped
class FoodRecipeRepository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDataSource

}