package np.com.mkishor.fooddy.data.sources.local

import kotlinx.coroutines.flow.Flow
import np.com.mkishor.fooddy.data.entities.FoodRecipeEntity
import javax.inject.Inject


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:44
 * @email:  mainalikishor@outlook.com
 *
 */
class LocalDataSource @Inject constructor(private val dao: FoodRecipeDao) {

    suspend fun insertFoodRecipes(foodRecipeEntity: FoodRecipeEntity) =
        dao.insertRecipes(foodRecipeEntity)

    fun readDatabase(): Flow<List<FoodRecipeEntity>> = dao.readRecipes()

}