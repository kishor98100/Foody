package np.com.mkishor.fooddy.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
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
}