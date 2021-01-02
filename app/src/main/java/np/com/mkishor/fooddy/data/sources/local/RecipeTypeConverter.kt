package np.com.mkishor.fooddy.data.sources.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import np.com.mkishor.fooddy.data.models.FoodRecipe


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 20:32
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun fromFoodRecipe(foodRecipe: FoodRecipe): String {
        val type = object : TypeToken<FoodRecipe>() {}.type
        return gson.toJson(foodRecipe, type)

    }

    @TypeConverter
    fun toFoodRecipe(foodRecipe: String): FoodRecipe {
        val type = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(foodRecipe, type)
    }


}