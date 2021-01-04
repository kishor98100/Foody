package np.com.mkishor.fooddy.data.sources.local

import kotlinx.coroutines.flow.Flow
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.data.entities.FoodJokeEntity
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

    fun readRecipes(): Flow<List<FoodRecipeEntity>> = dao.readRecipes()

    suspend fun insertFavoriteRecipes(favoriteRecipeEntity: FavoriteRecipeEntity) =
        dao.insertFavoriteRecipes(favoriteRecipeEntity)

    fun readFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>> = dao.readFavoriteRecipes()

    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity) =
        dao.deleteFavoriteRecipe(favoriteRecipeEntity)

    suspend fun deleteAllFavorites() = dao.deleteAllFavorites()

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) = dao.insertFoodJoke(foodJokeEntity)

    fun readFoodJoke(): Flow<List<FoodJokeEntity>> = dao.getFoodJoke()


}