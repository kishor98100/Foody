package np.com.mkishor.fooddy.utils.binding_adapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import np.com.mkishor.fooddy.data.entities.FoodJokeEntity
import np.com.mkishor.fooddy.data.models.FoodJoke
import np.com.mkishor.fooddy.utils.NetworkResult


/**
 * @author:  Kishor Mainali
 * @date:  04/01/2021 16:39
 * @email:  mainalikishor@outlook.com
 *
 */
class FoodJokeBindingAdapter {
    companion object {

        @BindingAdapter("app:apiResponse", "app:databaseResponse", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(
            view: View,
            readApiResponse: NetworkResult<FoodJoke>?,
            readDatabase: List<FoodJokeEntity>?
        ) {
            when (readApiResponse) {
                is NetworkResult.Loading -> {
                    view.run {
                        visibility = if (this is ProgressBar) View.VISIBLE else View.INVISIBLE
                    }
                }
                is NetworkResult.Error -> {
                    when (view) {
                        is MaterialCardView -> {
                            view.isVisible = !readDatabase.isNullOrEmpty()
                        }
                        else -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
                is NetworkResult.Success -> {
                    view.run {
                        visibility = if (this is MaterialCardView) View.VISIBLE else View.INVISIBLE
                    }
                }
            }

        }

        @BindingAdapter("app:apiResponseText", "app:databaseResponseText", requireAll = true)
        @JvmStatic
        fun setErrorViewVisibility(
            view: View,
            readApiResponse: NetworkResult<FoodJoke>?,
            readDatabase: List<FoodJokeEntity>?
        ) {

            if (readDatabase != null && readDatabase.isEmpty()) {
                view.visibility = View.VISIBLE
                if (view is TextView) {
                    view.text = readApiResponse?.message.toString()
                }
            }

            if (readApiResponse is NetworkResult.Success) {
                view.visibility = View.INVISIBLE
            }

        }

    }
}