package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
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
            view: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<FoodRecipeEntity>?
        ) {
            when (view) {
                is ImageView -> {
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                }
                is TextView -> {
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                    view.text = apiResponse?.message.toString()
                }
            }

        }
    }
}