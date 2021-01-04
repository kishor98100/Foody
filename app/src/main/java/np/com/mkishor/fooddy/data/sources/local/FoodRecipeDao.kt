package np.com.mkishor.fooddy.data.sources.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.data.entities.FoodRecipeEntity


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:28
 * @email:  mainalikishor@outlook.com
 *
 */
@Dao
interface FoodRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipeEntity: FoodRecipeEntity)

    @Query("SELECT * FROM recipes ORDER BY id ASC")
    fun readRecipes(): Flow<List<FoodRecipeEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipes(favoriteRecipeEntity: FavoriteRecipeEntity)


    @Query("SELECT * FROM favorites ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>>


    @Delete
    suspend fun deleteFavoriteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Query("DELETE  FROM favorites")
    suspend fun deleteAllFavorites()

}