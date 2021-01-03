package np.com.mkishor.fooddy.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import np.com.mkishor.fooddy.utils.Constants.Companion.IMAGE_BASE_URL


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 15:42
 * @email:  mainalikishor@outlook.com
 *
 */
@Parcelize
data class ExtendedIngredient(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("consistency")
    val consistency: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("original")
    val original: String,
    @SerializedName("unit")
    val unit: String
) : Parcelable {
    fun getImageUrl(): String {
        return "$IMAGE_BASE_URL$image"
    }
}
