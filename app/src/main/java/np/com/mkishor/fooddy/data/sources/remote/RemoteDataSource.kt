package np.com.mkishor.fooddy.data.sources.remote

import np.com.mkishor.fooddy.data.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:57
 * @email:  mainalikishor@outlook.com
 *
 */
class RemoteDataSource @Inject constructor(private val api: FoodRecipesApi) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> =
        api.getRecipes(queries)

}