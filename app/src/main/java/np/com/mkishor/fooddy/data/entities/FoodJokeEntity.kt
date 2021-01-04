package np.com.mkishor.fooddy.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import np.com.mkishor.fooddy.data.models.FoodJoke
import np.com.mkishor.fooddy.utils.Constants.Companion.FOOD_JOKE_TABLE


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 16:19
 * @email:  mainalikishor@outlook.com
 *
 */
@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}