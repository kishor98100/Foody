package np.com.mkishor.fooddy.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.utils.Constants.Companion.FAVORITE_RECIPE_TABLE


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 11:16
 * @email:  mainalikishor@outlook.com
 *
 */
@Entity(tableName = FAVORITE_RECIPE_TABLE)
class FavoriteRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int, var foodResult: FoodResult
)