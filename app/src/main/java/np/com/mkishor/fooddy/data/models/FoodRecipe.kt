package np.com.mkishor.fooddy.data.models

import com.google.gson.annotations.SerializedName


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:40
 * @email:  mainalikishor@outlook.com
 *
 */
data class FoodRecipe(
    @SerializedName("results")
    val results: List<FoodResult>

)
