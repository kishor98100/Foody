package np.com.mkishor.fooddy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import np.com.mkishor.fooddy.data.models.FoodRecipe
import np.com.mkishor.fooddy.data.sources.local.RecipeTypeConverter
import np.com.mkishor.fooddy.utils.Constants.Companion.RECIPE_TABLE


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:25
 * @email:  mainalikishor@outlook.com
 *
 */
@Entity(tableName = RECIPE_TABLE)
class FoodRecipeEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
