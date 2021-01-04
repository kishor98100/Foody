package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import np.com.mkishor.fooddy.data.entities.FoodRecipeEntity
import np.com.mkishor.fooddy.data.models.FoodRecipe
import np.com.mkishor.fooddy.utils.NetworkResult


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 21:25
 * @email:  mainalikishor@outlook.com
 *
 */

class RecipesBindingAdapter {
    companion object {
        @BindingAdapter("app:readApiResponse", "app:readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<FoodRecipeEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.GONE
            } else if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.GONE

            }

        }

        @BindingAdapter("app:readApiResponseText", "app:readDatabaseText", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView, apiResponse: NetworkResult<FoodRecipe>?,
            database: List<FoodRecipeEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else if (apiResponse is NetworkResult.Loading) {
                textView.visibility = View.GONE
            } else if (apiResponse is NetworkResult.Success) {
                textView.visibility = View.GONE

            }

        }

    }
}