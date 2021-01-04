package np.com.mkishor.fooddy.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import np.com.mkishor.fooddy.data.entities.FavoriteRecipeEntity
import np.com.mkishor.fooddy.data.entities.FoodJokeEntity
import np.com.mkishor.fooddy.data.entities.FoodRecipeEntity


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:37
 * @email:  mainalikishor@outlook.com
 *
 */

@Database(
    entities = [FoodRecipeEntity::class, FavoriteRecipeEntity::class, FoodJokeEntity::class],
    version = 3,
    exportSchema = false

)
@TypeConverters(RecipeTypeConverter::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun dao(): FoodRecipeDao
}