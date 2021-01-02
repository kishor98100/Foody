package np.com.mkishor.fooddy.data.sources.remote

import np.com.mkishor.fooddy.data.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:53
 * @email:  mainalikishor@outlook.com
 *
 */
interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>): Response<FoodRecipe>


}