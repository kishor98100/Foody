package np.com.mkishor.fooddy.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 16:06
 * @email:  mainalikishor@outlook.com
 *
 */
data class FoodJoke(
    @SerializedName("text")
    @Expose
    var text: String
)
