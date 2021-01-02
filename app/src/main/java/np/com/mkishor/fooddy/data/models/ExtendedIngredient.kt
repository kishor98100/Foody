package np.com.mkishor.fooddy.data.models

import com.google.gson.annotations.SerializedName


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:42
 * @email:  mainalikishor@outlook.com
 *
 */
data class ExtendedIngredient(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("consistency")
    val consistency: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("unit")
    val unit: String
)
