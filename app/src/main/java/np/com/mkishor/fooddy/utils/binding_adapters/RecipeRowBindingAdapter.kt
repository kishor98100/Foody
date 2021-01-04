package np.com.mkishor.fooddy.utils.binding_adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import np.com.mkishor.fooddy.R
import np.com.mkishor.fooddy.data.models.FoodResult
import np.com.mkishor.fooddy.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup


/**
 * @author:  Kishor Mainali
 * @date:  02/01/2021 19:29
 * @email:  mainalikishor@outlook.com
 *
 */
class RecipeRowBindingAdapter {

    companion object {
        const val TAG = "RecipeBinding"

        @BindingAdapter("app:setLikes")
        @JvmStatic
        fun setNumberOfLikes(textView: TextView, likes: Int) {
            textView.text = likes.toString()
        }

        @BindingAdapter("app:setMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView: TextView, minutes: Int) {
            textView.text = minutes.toString()
        }


        @BindingAdapter("app:veganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.run {
                            setTextColor(
                                ContextCompat.getColor(context, R.color.green)
                            )
                        }
                    }
                    is ImageView -> {
                        view.run {
                            setColorFilter(
                                ContextCompat.getColor(context, R.color.green)
                            )
                        }
                    }
                }
            }
        }

        @BindingAdapter("app:loadImage")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error)
            }
        }

        @BindingAdapter("app:setOnClickListener")
        @JvmStatic
        fun setOnClickListener(view: ConstraintLayout, foodResult: FoodResult) {
            view.setOnClickListener {
                try {
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(foodResult)
                    view.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.e(TAG, "setOnClickListener: ", e)
                }
            }
        }

        @BindingAdapter("app:parseHtml")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?) {

            if (description != null) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }

        }

        @BindingAdapter("app:setAmount", "app:unit", requireAll = true)
        @JvmStatic
        fun setIngredientAmount(textView: TextView, amount: Double, unit: String) {
            val text = "$amount $unit"
            textView.text = text

        }


    }


}